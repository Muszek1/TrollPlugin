package me.muszek_.troll.listeners;

import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class BlockToolUseListener implements Listener {

  private static final Set<Material> TOOLS = EnumSet.of(
      Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLDEN_AXE,
      Material.DIAMOND_AXE, Material.NETHERITE_AXE,
      Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE,
      Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE,
      Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL, Material.GOLDEN_SHOVEL,
      Material.DIAMOND_SHOVEL, Material.NETHERITE_SHOVEL,
      Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE,
      Material.DIAMOND_HOE, Material.NETHERITE_HOE,
      Material.SHEARS, Material.FLINT_AND_STEEL, Material.BUCKET, Material.WATER_BUCKET,
      Material.LAVA_BUCKET
  );

  private final Set<UUID> blocked = ConcurrentHashMap.newKeySet();

  public void lock(Player player) {
    blocked.add(player.getUniqueId());
  }

  public void unlock(Player player) {
    blocked.remove(player.getUniqueId());
  }

  public boolean isLocked(Player player) {
    return blocked.contains(player.getUniqueId());
  }

  @EventHandler
  public void onInteract(PlayerInteractEvent event) {
		if (event.getHand() != EquipmentSlot.HAND) {
			return;
		}
    Player player = event.getPlayer();
		if (!isLocked(player)) {
			return;
		}

    ItemStack item = event.getItem();
    if (item != null && TOOLS.contains(item.getType())) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();
		if (!isLocked(player)) {
			return;
		}
    ItemStack item = player.getInventory().getItemInMainHand();
    if (TOOLS.contains(item.getType())) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event) {
    Player player = event.getPlayer();
		if (!isLocked(player)) {
			return;
		}
    ItemStack item = event.getItemInHand();
    if (TOOLS.contains(item.getType())) {
      event.setCancelled(true);
    }
  }
}
