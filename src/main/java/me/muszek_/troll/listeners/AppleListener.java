package me.muszek_.troll.listeners;

import me.muszek_.troll.Colors;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class AppleListener implements Listener {

	private final JavaPlugin plugin;

	public AppleListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onAppleEat(PlayerItemConsumeEvent event) {
		ItemStack item = event.getItem();

		if (item.getType() == Material.ENCHANTED_GOLDEN_APPLE) {
			ItemMeta meta = item.getItemMeta();
			if (meta != null) {
				PersistentDataContainer container = meta.getPersistentDataContainer();
				NamespacedKey key = new NamespacedKey("troll", "apple");
				if (container.has(key, PersistentDataType.BYTE)) {
					event.setCancelled(true);
					event.getPlayer().sendMessage(Colors.color(Settings.Apple.APPLE_EATEN));
					Player player = event.getPlayer();
					ItemStack handItem = player.getInventory().getItemInMainHand();

					if (handItem.isSimilar(item)) {
						if (handItem.getAmount() > 1) {
							handItem.setAmount(handItem.getAmount() - 1);
						} else {
							player.getInventory().setItemInMainHand(null);
						}
					} else {
						ItemStack offHandItem = player.getInventory().getItemInOffHand();
						if (offHandItem.isSimilar(item)) {
							if (offHandItem.getAmount() > 1) {
								offHandItem.setAmount(offHandItem.getAmount() - 1);
							} else {
								player.getInventory().setItemInOffHand(null);
							}
						}
					}
					event.getPlayer().addPotionEffect(new org.bukkit.potion.PotionEffect(
							org.bukkit.potion.PotionEffectType.POISON,
							100,
							4
					));
				}
			}
		}
	}

}
