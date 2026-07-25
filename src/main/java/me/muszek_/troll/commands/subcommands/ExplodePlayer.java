package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@Command(name = "troll explode")
@Permission("epictroll.explodeplayer")
public class ExplodePlayer {

  private final Troll plugin;

  public ExplodePlayer(Troll plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {
    target.sendMessage(Colors.color(plugin.getMessageConfig().ExplodePlayer.Going_To_Explode));
    sender.sendMessage(Colors.color(plugin.getMessageConfig().ExplodePlayer.Message, "%player%", target.getName()));

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
          target.sendMessage(Colors.color(plugin.getMessageConfig().ExplodePlayer.You_Were_Blown_Up));
          cancel();
        }
      }
    }.runTaskTimer(plugin, 0L, 15L);
  }
}