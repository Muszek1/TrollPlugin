package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.listeners.ReverseChatListener;
import me.muszek_.troll.settings.Settings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "troll reversechat")
@Permission("epictroll.reversechat")
public class ReverseChat {

  private final ReverseChatListener listener;

  public ReverseChat(ReverseChatListener listener) {
    this.listener = listener;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {

    listener.toggle(target);

    if (listener.isReversed(target)) {
      sender.sendMessage(
          Colors.color(Settings.LangKey.REVERSEDCHAT_REVERSE.get(), "%player%", target.getName()));
    } else {
      sender.sendMessage(
          Colors.color(Settings.LangKey.REVERSEDCHAT_UNREVERSED.get(), "%player%", target.getName()));
    }
  }
}
