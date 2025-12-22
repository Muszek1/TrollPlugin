package me.muszek_.troll.commands.subcommands;

import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Freeze extends SubCommand {

  @Override
  public String getName() {
    return "freeze";
  }

  @Override
  public String getDescription() {
    return "freeze a player";
  }

  @Override
  public String getSyntax() {
    return "/troll freeze <player> <duration>";
  }

  @Override
  public void perform(Player player, String[] args) {

    if (args.length <= 1) {
      player.sendMessage(Colors.color(Settings.LangKey.FREEZE_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(player, args[1]);
    if (target == null) {
      return;
    }

    int time = 3;
    if (args.length >= 3) {
      try {
        time = Integer.parseInt(args[2]);
      } catch (NumberFormatException ex) {
        player.sendMessage(Colors.color(Settings.LangKey.FREEZE_INVALID_DURATION.get()));
        return;
      }
      if (time <= 0) {
        player.sendMessage(Colors.color(Settings.LangKey.FREEZE_INVALID_DURATION.get()));
        return;
      }
    }

    target.sendMessage(
        Colors.color(Settings.LangKey.FREEZE_TARGET_MESSAGE.get().replace("%player%", args[1])));
    player.sendMessage(Colors.color(
        Settings.LangKey.FREEZE_MESSAGE.get().replace("%player%", args[1])
            .replace("%time%", Integer.toString(time))));
    int finalTime = time;
    new BukkitRunnable() {
      int count = 0;

      @Override
      public void run() {
        if (count < finalTime) {
          target.setFreezeTicks(1000);
          count++;
        } else {
          cancel();
        }
      }
    }.runTaskTimer(Troll.getInstance(), 20L, 20L);
  }

  @Override
  public String getPermission() {
    return "epictroll.freeze";
  }


  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }

    return null;
  }
}
