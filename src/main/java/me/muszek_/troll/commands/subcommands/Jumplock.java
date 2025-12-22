package me.muszek_.troll.commands.subcommands;

import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.listeners.JumplockListener;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.entity.Player;

public class Jumplock extends SubCommand {

  private final JumplockListener listener;

  public Jumplock(JumplockListener listener) {
    this.listener = listener;
  }

  @Override
  public String getName() {
    return "jumplock";
  }

  @Override
  public String getDescription() {
    return "Blocks jumping for the target player.";
  }

  @Override
  public String getSyntax() {
    return "/troll jumplock <player>";
  }

  @Override
  public void perform(Player sender, String[] args) {

    if (args.length <= 1) {
      sender.sendMessage(Colors.color(Settings.LangKey.JUMPLOCK_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(sender, args[1]);
    if (target == null) {
      return;
    }

    if (listener.isLocked(target)) {
      listener.unlock(target);
      sender.sendMessage(
          Colors.color(Settings.LangKey.JUMPLOCK_UNLOCK.get()).replace("%player%", args[1]));
    } else {
      listener.lock(target);
      sender.sendMessage(
          Colors.color(Settings.LangKey.JUMPLOCK_LOCK.get()).replace("%player%", args[1]));
    }
  }

  @Override
  public String getPermission() {
    return "epictroll.jumplock";
  }

  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }

    return null;
  }
}
