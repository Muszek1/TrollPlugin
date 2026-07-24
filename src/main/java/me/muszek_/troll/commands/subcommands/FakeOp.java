package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.settings.Settings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "troll fakeop")
@Permission("epictroll.fakeop")
public class FakeOp{

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {

    sender.sendMessage(
        Colors.color(Settings.LangKey.FAKEOP_MESSAGE_SENT.get(), "%player%", target.getName()));
    target.sendMessage(
        Colors.color(Settings.LangKey.FAKEOP_MESSAGE_CONFIRMATION.get(), "%player%", target.getName()));


  }
}
