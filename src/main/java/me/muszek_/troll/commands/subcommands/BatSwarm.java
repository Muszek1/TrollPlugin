package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.optional.OptionalArg;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Command(name = "troll batswarm")
@Permission("epictroll.batswarm")
public class BatSwarm {

    private final Troll plugin;
    private final Random random = new Random();

    public BatSwarm(Troll plugin) {
        this.plugin = plugin;
    }

    @Execute
    public void execute(@Context CommandSender sender,
                        @Arg("player") Player target,
                        @OptionalArg("amount") Integer numberOfBats) {

        if (numberOfBats == null || numberOfBats == 0) {
            numberOfBats = plugin.getPluginConfig().BatSwarm.Default_Number;
        }

        target.playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 0.5f);
        target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 1, false, false));

        double radius = 3.0;
        List<Entity> spawnedBats = new ArrayList<>();

        for (int i = 0; i < numberOfBats; i++) {
            double angle = 2 * Math.PI * i / numberOfBats;

            double x = target.getLocation().getX() + radius * Math.cos(angle);
            double z = target.getLocation().getZ() + radius * Math.sin(angle);
            double y = target.getLocation().getY() + 0.5;

            Location spawnLocation = new Location(target.getWorld(), x, y, z);
            Entity entity = target.getWorld().spawnEntity(spawnLocation, EntityType.BAT);

            entity.setGravity(false);
            entity.setFireTicks(200);

            Vector velocity = target.getLocation().toVector()
                    .subtract(spawnLocation.toVector())
                    .normalize()
                    .multiply(1.2);
            entity.setVelocity(velocity);

            spawnedBats.add(entity);
        }

        sender.sendMessage(Colors.color(
                plugin.getMessageConfig().BatSwarm.Sent,
                "%amount%", String.valueOf(numberOfBats),
                "%player%", target.getName()
        ));

        int durationTicks = 60 + random.nextInt(41);

        new BukkitRunnable() {
            int ticksElapsed = 0;

            @Override
            public void run() {
                ticksElapsed += 5;

                if (ticksElapsed >= durationTicks || !target.isOnline()) {
                    for (Entity bat : spawnedBats) {
                        if (bat != null && !bat.isDead()) {
                            bat.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, bat.getLocation(), 5, 0.2, 0.2, 0.2, 0.02);
                            bat.remove();
                        }
                    }
                    this.cancel();
                    return;
                }

                for (Entity bat : spawnedBats) {
                    if (bat != null && !bat.isDead()) {
                        Vector toPlayer = target.getLocation().add(0, 0.5, 0).toVector().subtract(bat.getLocation().toVector());
                        bat.setVelocity(toPlayer.normalize().multiply(0.3));
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 5L);
    }
}
