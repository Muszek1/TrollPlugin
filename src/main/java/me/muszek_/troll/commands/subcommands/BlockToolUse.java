package me.muszek_.troll.commands.subcommands;

import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.listeners.BlockToolUseListener;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.entity.Player;

public class BlockToolUse extends SubCommand {

  private final BlockToolUseListener listener;

  public BlockToolUse(BlockToolUseListener listener) {
    this.listener = listener;
  }

  @Override
  public String getName() {
    return "blocktooluse";
  }

  @Override
  public String getDescription() {
    return "block tool use";
  }

  @Override
  public String getSyntax() {
    return "/troll blocktooluse <player>";
  }

  @Override
  public void perform(Player sender, String[] args) {

    if (args.length == 1) {
      sender.sendMessage(Colors.color(Settings.LangKey.BLOCKTOOLUSE_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(sender, args[1]);
    if (target == null) {
      return;
    }

    if (listener.isLocked(target)) {
      listener.unlock(target);
      sender.sendMessage(
          Colors.color(Settings.LangKey.BLOCKTOOLUSE_UNLOCK.get().replace("%player%", args[1])));
    } else {
      listener.lock(target);
      sender.sendMessage(
          Colors.color(Settings.LangKey.BLOCKTOOLUSE_BLOCK.get().replace("%player%", args[1])));
    }
  }

  @Override
  public String getPermission() {
    return "epictroll.blocktooluse";
  }

  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }
    return null;
  }
}
