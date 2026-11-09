package me.muszek_.troll.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class HoleHandsListener implements Listener {

  private final Set<UUID> enabledPlayers = ConcurrentHashMap.newKeySet();

  public void enable(Player player) {
    enabledPlayers.add(player.getUniqueId());
  }

  public void disable(Player player) {
    enabledPlayers.remove(player.getUniqueId());
  }

  public boolean isEnabled(Player player) {
    return enabledPlayers.contains(player.getUniqueId());
  }

  @EventHandler(ignoreCancelled = true)
  public void onInteract(PlayerInteractEvent event) {
    if (event.getHand() != EquipmentSlot.HAND) {
      return;
    }

    Player player = event.getPlayer();
    if (!isEnabled(player)) {
      return;
    }

    ItemStack heldItem = player.getInventory().getItemInMainHand();
    if (heldItem.getType() == Material.AIR) {
      return;
    }

    event.setCancelled(true);

    ItemStack droppedStack = heldItem.clone();
    player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));

    Item droppedItem = player.getWorld().dropItemNaturally(
        player.getLocation().add(0, 1, 0),
        droppedStack
    );
    droppedItem.setPickupDelay(20);
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    disable(event.getPlayer());
  }
}
