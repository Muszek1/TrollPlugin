package me.muszek_.troll.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LaunchListener implements Listener {

    private final JavaPlugin plugin;
    private static final Map<UUID, Long> noFallUntil = new ConcurrentHashMap<>();

    public LaunchListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public static void grantNoFall(Player player, long millis) {
        noFallUntil.put(player.getUniqueId(), System.currentTimeMillis() + millis);
        player.setFallDistance(0f);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFallDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;

        UUID id = player.getUniqueId();
        Long until = noFallUntil.get(id);

        // DEBUG — wypisujemy szczegóły
        plugin.getLogger().info("[DEBUG] " + player.getName() +
                " cause=FALL " +
                "fallDistance=" + player.getFallDistance() +
                " velY=" + player.getVelocity().getY() +
                " hasFlag=" + (until != null) +
                " timeLeft=" + (until == null ? "null" : (until - System.currentTimeMillis()) + "ms"));

        if (until == null) return;

        noFallUntil.remove(id);

        if (System.currentTimeMillis() > until) {
            plugin.getLogger().info("[DEBUG] Ochrona wygasła — obrażenia normalne.");
            return;
        }

        plugin.getLogger().info("[DEBUG] Anuluję obrażenia od upadku dla " + player.getName());
        event.setCancelled(true);
        player.setFallDistance(0f);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        noFallUntil.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        noFallUntil.remove(event.getEntity().getUniqueId());
    }
}
