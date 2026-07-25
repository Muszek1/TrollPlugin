package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Command(name = "troll fakexp")
@Permission("epictroll.fakexp")
public class FakeXp implements Listener {

  private final Troll plugin;
  private static final Map<UUID, LevelExp> restoreMap = new HashMap<>();

  public FakeXp(Troll plugin) {
    this.plugin = plugin;
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target, @Arg("number") int number) {
    restoreMap.put(target.getUniqueId(), new LevelExp(target.getLevel(), target.getExp()));

    new BukkitRunnable() {
      int given = 0;

      @Override
      public void run() {
        if (given >= number) {
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

    sender.sendMessage(Colors.color(plugin.getMessageConfig().Fakexp.Given, "%player%", target.getName(), "%amount%", String.valueOf(number)));
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
}