package me.muszek_.troll.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class DiamondListener implements Listener {

  public DiamondListener() {
  }

  @EventHandler
  public void onItemPickup(PlayerAttemptPickupItemEvent event) {
    ItemStack item = event.getItem().getItemStack();
    ItemMeta meta = item.getItemMeta();
    if (meta == null) {
      return;
    }

    NamespacedKey key = new NamespacedKey("troll", "diamond");
    if (meta.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
      event.setCancelled(true);
    }
  }
}
