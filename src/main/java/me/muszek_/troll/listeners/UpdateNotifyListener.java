package me.muszek_.troll.listeners;

import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateNotifyListener implements Listener {

  private final Troll plugin;

  public UpdateNotifyListener(Troll plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();

		if (!player.isOp() && !player.hasPermission("*")) {
			return;
		}
		if (!plugin.isUpdateAvailable()) {
			return;
		}

    Bukkit.getScheduler().runTaskLater(plugin, () -> player.sendMessage(
            Colors.color("&7[&2EpicTroll&7] &fPlugin &2EpicTroll&f has new update " +
                plugin.getLatestVersion() + "&f! Check:&b https://www.spigotmc.org/resources/124041/")),
        60L);
  }
}
