package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.optional.OptionalArg;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.settings.Settings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@Command(name = "troll freeze")
@Permission("epictroll.freeze")
public class Freeze {

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target, @OptionalArg("seconds") Integer number) {

    Integer time = number;
      if (time <= 0) {
        time = Settings.ConfigKey.FREEZE_DEFAULT_DURATION.get();;
        sender.sendMessage(Colors.color(Settings.LangKey.FREEZE_INVALID_DURATION.get()));
      }


    target.sendMessage(
        Colors.color(Settings.LangKey.FREEZE_TARGET_MESSAGE.get().replace("%player%", sender.getName())));
    sender.sendMessage(Colors.color(
        Settings.LangKey.FREEZE_MESSAGE.get(), "%player%", target.getName(), "%time%",
        Integer.toString(time)));
    int finalTime = time;
    new BukkitRunnable() {
      int count = 0;

      @Override
      public void run() {
        if (!target.isOnline()) {
          cancel();
          return;
        }

        if (count >= finalTime) {
          target.setFreezeTicks(0);
          cancel();
          return;
        }

        target.setFreezeTicks(1000);
        count++;
      }
    }.runTaskTimer(Troll.getInstance(), 0L, 20L);
  }
}
