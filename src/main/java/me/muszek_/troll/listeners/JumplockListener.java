package me.muszek_.troll.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class JumplockListener implements Listener {

  private final Set<UUID> jumpLocked = new HashSet<>();

  public void lock(Player player) {
    jumpLocked.add(player.getUniqueId());
    player.addPotionEffect(
        new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 128, false, false, false));
  }

  public void unlock(Player player) {
    jumpLocked.remove(player.getUniqueId());
    player.removePotionEffect(PotionEffectType.JUMP);
  }


  public boolean isLocked(Player player) {
    return jumpLocked.contains(player.getUniqueId());
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    if (isLocked(player)) {
      unlock(player);
    }
  }

  public Set<UUID> getJumpLocked() {
    return jumpLocked;
  }

}
