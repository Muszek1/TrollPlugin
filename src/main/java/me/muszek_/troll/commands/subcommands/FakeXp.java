package me.muszek_.troll.commands.subcommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FakeXp extends SubCommand implements Listener {

  private final JavaPlugin plugin;
  private static final Map<UUID, LevelExp> restoreMap = new HashMap<>();

  public FakeXp(JavaPlugin plugin) {
    this.plugin = plugin;
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }

  @Override
  public String getName() {
    return "fakexp";
  }

  @Override
  public String getDescription() {
    return "give a fake XP";
  }

  @Override
  public String getSyntax() {
    return "/troll fakexp <player> <amount>";
  }

  @Override
  public void perform(Player sender, String[] args) {
    if (args.length < 2) {
      sender.sendMessage(Colors.color(Settings.LangKey.FAKEXP_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(sender, args[1]);
    if (target == null) {
      return;
    }

    int amount = 200;
    if (args.length >= 3) {
      try {
        amount = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        sender.sendMessage(Colors.color(Settings.LangKey.WRONG_NUMBER.get()));
        return;
      }
    }

    restoreMap.put(target.getUniqueId(), new LevelExp(target.getLevel(), target.getExp()));

    int finalAmount = amount;
    new BukkitRunnable() {
      int given = 0;

      @Override
      public void run() {
        if (given >= finalAmount) {
          restoreOriginal(target);
          cancel();
          return;
        }
        if (!target.isOnline()) {
          cancel();
          return;
        }
        target.giveExp(1);
        given++;
      }
    }.runTaskTimer(plugin, 0L, 1L);

    sender.sendMessage(Colors.color(Settings.LangKey.FAKEXP_GIVEN.get()
        .replace("%player%", target.getName())
        .replace("%amount%", String.valueOf(amount))));
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    UUID id = event.getPlayer().getUniqueId();
    if (restoreMap.containsKey(id)) {
      LevelExp orig = restoreMap.remove(id);
      event.getPlayer().setLevel(orig.level);
      event.getPlayer().setExp(orig.exp);
    }
  }

  private void restoreOriginal(Player target) {
    UUID id = target.getUniqueId();
    if (restoreMap.containsKey(id)) {
      LevelExp orig = restoreMap.remove(id);
      target.setLevel(orig.level);
      target.setExp(orig.exp);
    }
  }

  private static class LevelExp {

    final int level;
    final float exp;

    LevelExp(int level, float exp) {
      this.level = level;
      this.exp = exp;
    }
  }


  @Override
  public String getPermission() {
    return "epictroll.fakexp";
  }

  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }

    return null;
  }
}

