package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.listeners.HoleHandsListener;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "troll holehands")
@Permission("epictroll.holehands")
public class HoleHands {

  private final HoleHandsListener listener;
  private final Troll plugin;

  public HoleHands(HoleHandsListener listener, Troll plugin) {
    this.listener = listener;
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {
    if (listener.isEnabled(target)) {
      listener.disable(target);
      sender.sendMessage(Colors.color(
          plugin.getMessageConfig().HoleHands.Disabled,
          "%player%",
          target.getName()
      ));
    } else {
      listener.enable(target);
      sender.sendMessage(Colors.color(
          plugin.getMessageConfig().HoleHands.Enabled,
          "%player%",
          target.getName()
      ));
    }
  }
}
