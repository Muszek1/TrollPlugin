package me.muszek_.troll.commands.subcommands;

import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.listeners.ReverseChatListener;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.entity.Player;

public class ReverseChat extends SubCommand {

  private final ReverseChatListener listener;

  public ReverseChat(ReverseChatListener listener) {
    this.listener = listener;
  }


  @Override
  public String getName() {
    return "reversechat";
  }

  @Override
  public String getDescription() {
    return "player sees the reversed chat";
  }

  @Override
  public String getSyntax() {
    return "/troll reversechat <player>";
  }

  @Override
  public void perform(Player sender, String[] args) {

    if (args.length == 1) {
      sender.sendMessage(Colors.color(Settings.LangKey.REVERSEDCHAT_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(sender, args[1]);
    if (target == null) {
      return;
    }

    listener.toggle(target);

    if (listener.isReversed(target)) {
      sender.sendMessage(
          Colors.color(Settings.LangKey.REVERSEDCHAT_REVERSE.get(), "%player%", args[1]));
    } else {
      sender.sendMessage(
          Colors.color(Settings.LangKey.REVERSEDCHAT_UNREVERSED.get(), "%player%", args[1]));
    }
  }

  @Override
  public String getPermission() {
    return "epictroll.reversechat";
  }

  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }

    return null;
  }
}
