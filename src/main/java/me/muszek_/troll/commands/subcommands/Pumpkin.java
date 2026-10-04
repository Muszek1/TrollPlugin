package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.optional.OptionalArg;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Command(name = "troll pumpkin")
@Permission("epictroll.pumpkin")
public class Pumpkin implements Listener {

    private final Troll plugin;
    private static final Map<UUID, ItemStack> restoreMap = new HashMap<>();
    private static final Map<UUID, BukkitTask> activeTasks = new HashMap<>();


    public Pumpkin(Troll plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Execute
    public void execute(@Context CommandSender sender, @Arg("player") Player target, @OptionalArg("seconds") Integer seconds) {

        if (seconds == null || seconds == 0) {
            seconds = plugin.getPluginConfig().Pumpkin.Default_Duration;
        }
        UUID uuid = target.getUniqueId();
        if (!restoreMap.containsKey(uuid)) {
            restoreMap.put(uuid, target.getInventory().getHelmet());
        }

        if (activeTasks.containsKey(uuid)) {
            activeTasks.get(uuid).cancel();
        }


        target.getInventory().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
        target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, seconds * 20, 0));

        int finalSeconds = seconds;
        BukkitTask task = new BukkitRunnable() {
            int duration = 0;


            @Override
            public void run() {
                if (!target.isOnline()) {
                    cancel();
                    activeTasks.remove(uuid);
                    return;
                }
                if (duration >= finalSeconds * 20) {
                    restoreOriginal(target);
                    cancel();
                    return;
                }
                duration++;
            }
        }.runTaskTimer(plugin, 0L, 1L);


        activeTasks.put(uuid, task);

        sender.sendMessage(Colors.color(plugin.getMessageConfig().Pumpkin.Put, "%player%", target.getName()));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID id = event.getPlayer().getUniqueId();
        if (restoreMap.containsKey(id)) {
            ItemStack orig = restoreMap.remove(id);
            if (orig != null && orig.getType() != Material.AIR) {
                event.getPlayer().getInventory().setHelmet(orig);
            } else {
                event.getPlayer().getInventory().setHelmet(null);
            }
            event.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
        }
    }

    private void restoreOriginal(Player target) {
        UUID id = target.getUniqueId();
        ItemStack orig = restoreMap.remove(id);
        if (orig != null && orig.getType() != Material.AIR) {
            target.getInventory().setHelmet(orig);
        } else {
            target.getInventory().setHelmet(null);
        }

        BukkitTask task = activeTasks.remove(id);
        if (task != null) {
            task.cancel();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID id = player.getUniqueId();

        ItemStack orig = restoreMap.remove(id);
        if (orig != null) {
            player.getInventory().setHelmet(orig);
        } else {
            player.getInventory().setHelmet(null);
        }

        event.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);

        BukkitTask task = activeTasks.remove(id);
        if (task != null) {
            task.cancel();
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getSlot() == 39 && activeTasks.containsKey(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
