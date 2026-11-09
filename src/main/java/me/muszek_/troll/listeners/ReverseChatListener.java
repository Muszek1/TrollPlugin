package me.muszek_.troll.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Set;
import java.util.UUID;

public class ReverseChatListener implements Listener {

  private final Set<UUID> reversedChatTargets =
          java.util.concurrent.ConcurrentHashMap.newKeySet();

  public void toggle(Player player) {
    UUID uuid = player.getUniqueId();
    if (!reversedChatTargets.add(uuid)) {
      reversedChatTargets.remove(uuid);
    }
  }

  public boolean isReversed(Player player) {
    return reversedChatTargets.contains(player.getUniqueId());
  }

  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onChat(AsyncPlayerChatEvent event) {
    if (!isReversed(event.getPlayer())) {
      return;
    }

    event.setMessage(
            new StringBuilder(event.getMessage()).reverse().toString()
    );
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    if (isReversed(player)) {
      toggle(player);
    }
  }
}