package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.listeners.LaunchListener;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@Command(name = "troll launch")
@Permission("epictroll.launch")
public class Launch {

  private final Troll plugin;

  public Launch(Troll plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {

    target.setVelocity(new Vector(0, 2.5, 0));
    LaunchListener.grantNoFall(target, 5000L);
    target.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, target.getLocation(), 20, 0.5, 0.5, 0.5);
    target.getWorld().spawnParticle(Particle.CLOUD, target.getLocation(), 10, 0.5, 0.5, 0.5);
    target.getWorld().playSound(target.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1f, 1f);
    target.setFallDistance(0f);

    sender.sendMessage(Colors.color(plugin.getMessageConfig().Launch.Launched, "%player%", target.getName()));
  }
}