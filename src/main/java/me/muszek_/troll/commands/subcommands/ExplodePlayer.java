package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@Command(name = "troll explode")
@Permission("epictroll.explodeplayer")
public class ExplodePlayer {

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {
    target.sendMessage(Colors.color(Settings.LangKey.EXPLODEPLAYER_GOING_TO_EXPLODE.get()));
    sender.sendMessage(
            Colors.color(Settings.LangKey.EXPLODEPLAYER_MESSAGE.get()
                    .replace("%player%", target.getName()))
    );
    new BukkitRunnable() {
      int count = 0;

      @Override
      public void run() {
        if (count < 3) {
          target.playSound(target.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1, 1);
          count++;
        } else {
          target.playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
          target.setHealth(0);
          target.sendMessage(
                  Colors.color(Settings.LangKey.EXPLODEPLAYER_YOU_WERE_BLOWN_UP.get()));
          cancel();
        }
      }
    }.runTaskTimer(Troll.getInstance(), 0L, 15L);

     }

}
