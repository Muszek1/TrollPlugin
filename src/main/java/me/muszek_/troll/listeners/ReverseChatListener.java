package me.muszek_.troll.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ReverseChatListener implements Listener {

  private final Set<UUID> reversedChatTargets = new HashSet<>();

  public void toggle(Player player) {
    UUID uuid = player.getUniqueId();
    if (!reversedChatTargets.add(uuid)) {
      reversedChatTargets.remove(uuid);
    }
  }

  public boolean isReversed(Player player) {
    return reversedChatTargets.contains(player.getUniqueId());
  }

  @EventHandler
  public void onChat(AsyncChatEvent event) {
    Player player = event.getPlayer();
    if (!isReversed(player)) {
      return;
    }

    Component originalComponent = event.message();

    String plainText = PlainTextComponentSerializer.plainText().serialize(originalComponent);

    String reversedText = new StringBuilder(plainText).reverse().toString();

    event.message(Component.text(reversedText));
  }
}