package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.listeners.ReverseChatListener;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "troll reversechat")
@Permission("epictroll.reversechat")
public class ReverseChat {

  private final ReverseChatListener listener;
  private final Troll plugin;

  public ReverseChat(ReverseChatListener listener, Troll plugin) {
    this.listener = listener;
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {
    listener.toggle(target);
    if (listener.isReversed(target)) {
      sender.sendMessage(Colors.color(plugin.getMessageConfig().Reversechat.Reverse, "%player%", target.getName()));
    } else {
      sender.sendMessage(Colors.color(plugin.getMessageConfig().Reversechat.Unreversed, "%player%", target.getName()));
    }
  }
}