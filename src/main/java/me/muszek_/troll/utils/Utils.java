package me.muszek_.troll.utils;

import me.muszek_.troll.Colors;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {

  public static Player getTarget(CommandSender sender, String name) {

    Player target = Bukkit.getPlayerExact(name);
    if (target == null) {
      sender.sendMessage(
          Colors.color(Settings.LangKey.PLAYER_NOT_FOUND.get().replace("%player%", name)));
      return null;
    }
    return target;

  }
}