package me.muszek_.troll.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LaunchListener implements Listener {

	private final JavaPlugin plugin;

	public LaunchListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onFallDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player player)) return;

		if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
			// Optional: check metadata or PersistentDataContainer to know it's our troll
			event.setCancelled(true);
		}
	}
}