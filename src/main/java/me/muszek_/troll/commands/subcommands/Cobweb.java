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
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@Command(name = "troll cobweb")
@Permission("epictroll.cobweb")
public class Cobweb {

    private final Troll plugin;

    public Cobweb(Troll plugin) {
        this.plugin = plugin;
    }

    @Execute
    public void execute(@Context CommandSender sender, @Arg("player") Player target, @OptionalArg("seconds") Integer seconds) {
        Location loc = target.getLocation();
        int[][] offsets = {{0, 0, 0}, {0, 1, 0},
                {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1},
                {1, 1, 0}, {-1, 1, 0}, {0, 1, 1}, {0, 1, -1},
                {0, 2, 0}
        };
        if (seconds == null || seconds == 0) {
            seconds = plugin.getPluginConfig().Cobweb.Default_Duration;
        }

        Integer finalSeconds = seconds;
        new BukkitRunnable() {
            int ticksPassed = 0;

            final int maxTicks = finalSeconds * 20;

            @Override
            public void run() {
                if (!target.isOnline() || ticksPassed >= maxTicks) {
                    if (target.isOnline()) {
                        for (int[] offset : offsets) {
                            Location webLoc = loc.clone().add(offset[0], offset[1], offset[2]);
                            target.sendBlockChange(webLoc, webLoc.getBlock().getBlockData());
                        }
                    }
                    this.cancel();
                    return;
                }

                for (int[] offset : offsets) {
                    Location webLoc = loc.clone().add(offset[0], offset[1], offset[2]);
                    if (webLoc.getBlock().getType() == Material.AIR) {
                        target.sendBlockChange(webLoc, Material.COBWEB.createBlockData());
                    }
                }

                ticksPassed += 10;
            }
        }.runTaskTimer(plugin, 0L, 10L);

        sender.sendMessage(Colors.color(plugin.getMessageConfig().Cobweb.Sent, "%player%", target.getName()));
    }
}