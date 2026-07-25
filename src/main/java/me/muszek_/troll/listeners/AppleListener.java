package me.muszek_.troll.listeners;

import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.config.MessageConfig;
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

public class AppleListener implements Listener {

  private final Troll plugin;
  private final MessageConfig messageConfig;
  private final NamespacedKey appleKey;

  public AppleListener(Troll plugin, MessageConfig messageConfig) {
    this.plugin = plugin;
    this.messageConfig = messageConfig;
    this.appleKey = new NamespacedKey(plugin, "apple");
  }

  @EventHandler
  public void onAppleEat(PlayerItemConsumeEvent event) {
    ItemStack item = event.getItem();

    if (item.getType() == Material.ENCHANTED_GOLDEN_APPLE) {
      ItemMeta meta = item.getItemMeta();
      if (meta != null) {
        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (container.has(appleKey, PersistentDataType.BYTE)) {
          event.setCancelled(true);
          event.getPlayer().sendMessage(Colors.color(plugin.getMessageConfig().Apple.Eaten, "%player%", event.getPlayer().getName()));
          Player player = event.getPlayer();
          ItemStack handItem = player.getInventory().getItemInMainHand();

          if (handItem.isSimilar(item)) {
            if (player.getGameMode() != org.bukkit.GameMode.CREATIVE) {
              if (handItem.getAmount() > 1) {
                handItem.setAmount(handItem.getAmount() - 1);
              } else {
                player.getInventory().setItemInMainHand(null);
              }
            }
          } else {
            ItemStack offHandItem = player.getInventory().getItemInOffHand();
            if (offHandItem.isSimilar(item)) {
              if (player.getGameMode() != org.bukkit.GameMode.CREATIVE) {
                if (offHandItem.getAmount() > 1) {
                  offHandItem.setAmount(offHandItem.getAmount() - 1);
                } else {
                  player.getInventory().setItemInOffHand(null);
                }
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
