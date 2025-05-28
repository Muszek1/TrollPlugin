package me.muszek_.troll.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (!isReversed(player)) return;

		String original = event.getMessage();
		String reversed = new StringBuilder(original).reverse().toString();
		event.setMessage(reversed);
	}
}