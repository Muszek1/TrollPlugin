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
import org.bukkit.scheduler.BukkitRunnable;

@Command(name = "troll freeze")
@Permission("epictroll.freeze")
public class Freeze {

  private final Troll plugin;

  public Freeze(Troll plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target, @OptionalArg("seconds") Integer number) {
    int time = (number == null || number <= 0) ? plugin.getPluginConfig().Freeze.Default_Duration : number;

    target.sendMessage(Colors.color(plugin.getMessageConfig().Freeze.Target_Message, "%player%", sender.getName()));
    sender.sendMessage(Colors.color(plugin.getMessageConfig().Freeze.Message, "%player%", target.getName(), "%time%", String.valueOf(time)));

    new BukkitRunnable() {
      int count = 0;
      @Override
      public void run() {
        if (!target.isOnline()) {
          cancel();
          return;
        }
        if (count >= time) {
          target.setFreezeTicks(0);
          cancel();
          return;
        }
        target.setFreezeTicks(1000);
        count++;
      }
    }.runTaskTimer(plugin, 0L, 20L);
  }
}