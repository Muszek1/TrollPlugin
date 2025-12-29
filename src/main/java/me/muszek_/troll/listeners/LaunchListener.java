package me.muszek_.troll.listeners;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LaunchListener implements Listener {

  private static final Map<UUID, Long> noFallUntil = new ConcurrentHashMap<>();

  public LaunchListener() {
  }

  public static void grantNoFall(Player player, long millis) {
    noFallUntil.put(player.getUniqueId(), System.currentTimeMillis() + millis);
    player.setFallDistance(0f);
  }

  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  public void onFallDamage(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof Player player)) {
      return;
    }
    if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
      return;
    }

    UUID id = player.getUniqueId();
    Long until = noFallUntil.get(id);

    if (until == null) {
      return;
    }

    noFallUntil.remove(id);

    if (System.currentTimeMillis() > until) {
      return;
    }

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
