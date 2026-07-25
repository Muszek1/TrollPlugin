package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "troll fakeop")
@Permission("epictroll.fakeop")
public class FakeOp {

  private final Troll plugin;

  public FakeOp(Troll plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {
    sender.sendMessage(Colors.color(plugin.getMessageConfig().Fakeop.Message_Confirmation, "%player%", target.getName()));
    target.sendMessage(Colors.color(plugin.getMessageConfig().Fakeop.Message_Sent, "%player%", target.getName()));
  }
}