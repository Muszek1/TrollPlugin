package me.muszek_.troll.listeners;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class CookieListener implements Listener {

  public CookieListener() {
  }

  @EventHandler
  public void onEatCookie(PlayerItemConsumeEvent event) {
    ItemStack item = event.getItem();
    if (item.getType() != Material.COOKIE) {
      return;
    }

    ItemMeta meta = item.getItemMeta();
    if (meta == null) {
      return;
    }

    NamespacedKey key = new NamespacedKey("troll", "cookie");
    if (meta.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
      event.setCancelled(true);

      Player player = event.getPlayer();
      player.setFoodLevel(Math.min(20, player.getFoodLevel() + 2));
      player.setSaturation(Math.min(20, player.getSaturation() + 1f));

    }
  }
}
