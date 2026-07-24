package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.optional.OptionalArg;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.settings.Settings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "troll fire")
@Permission("epictroll.fire")
public class Fire {

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target, @OptionalArg("number") Integer number) {
    Integer time = number;
    if (number == null){
      time = Settings.ConfigKey.FIRE_DEFAULT_DURATION.get();}

    if (time <= 0) {
      sender.sendMessage(Colors.color(Settings.LangKey.FIRE_INVALID_DURATION.get()));
      return;
    }
    target.setFireTicks(20 * time);

    target.sendMessage(Colors.color(
          Settings.LangKey.FIRE_MESSAGE.get().replace("%player%", target.getName())
              .replace("%time%", Integer.toString(time))));


      sender.sendMessage(Colors.color(
          Settings.LangKey.FIRE_MESSAGE.get().replace("%player%", target.getName())
              .replace("%time%", Integer.toString(time))));
    }
  }
