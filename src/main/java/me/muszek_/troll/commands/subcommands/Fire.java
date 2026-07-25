package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.optional.OptionalArg;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "troll fire")
@Permission("epictroll.fire")
public class Fire {

  private final Troll plugin;

  public Fire(Troll plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target, @OptionalArg("seconds") Integer number) {
    int time = (number == null) ? plugin.getPluginConfig().Fire.Default_Duration : number;

    if (time <= 0) {
      sender.sendMessage(Colors.color(plugin.getMessageConfig().Fire.Invalid_Duration));
      return;
    }

    target.setFireTicks(20 * time);
    String msg = plugin.getMessageConfig().Fire.Message;

    sender.sendMessage(Colors.color(msg, "%player%", target.getName(), "%time%", String.valueOf(time)));
  }
}