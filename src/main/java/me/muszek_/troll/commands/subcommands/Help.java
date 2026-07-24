package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import org.bukkit.command.CommandSender;

@Command(name = "troll help")
@Permission("epictroll.help")
public class Help {

  @Execute
  public void execute(@Context CommandSender sender) {

    sender.sendMessage(Colors.color(
        "&e> &fIf you need help, join to our discord: https://discord.gg/cT5MxqAYTd.\nList of commands are on spigot page. Or use &e&l/troll gui <player>"));

  }

}
