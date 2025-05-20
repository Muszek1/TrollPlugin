package me.muszek_.troll.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BlockCraftListener implements Listener {

	private final Set<UUID> blockcraft = new HashSet<>();


	public void lock(Player player) {
		blockcraft.add(player.getUniqueId());
	}

	public void unlock(Player player) {
		blockcraft.remove(player.getUniqueId());
	}


	public boolean isLocked(Player player) {
		return blockcraft.contains(player.getUniqueId());
	}


	@EventHandler
	public void onCraft(PrepareItemCraftEvent event) {
		if (!(event.getView().getPlayer() instanceof Player player)) return;

		if (isLocked(player)) {
			event.getInventory().setResult(null);
		}
	}

	@EventHandler
	public void onCraftItem(CraftItemEvent event) {
		if (!(event.getWhoClicked() instanceof Player player)) return;

		if (isLocked(player)) {
			event.setCancelled(true);
		}
	}
}
