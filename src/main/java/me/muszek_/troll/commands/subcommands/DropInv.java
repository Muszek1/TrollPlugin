package me.muszek_.troll.commands.subcommands;

import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class DropInv extends SubCommand {

  @Override
  public String getName() {
    return "dropinv";
  }

  @Override
  public String getDescription() {
    return "drops a player's inventory";
  }

  @Override
  public String getSyntax() {
    return "/troll dropinv <player>";
  }

  @Override
  public void perform(Player player, String[] args) {

    if (args.length == 1) {
      player.sendMessage(Colors.color(Settings.LangKey.DROPINV_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(player, args[1]);
    if (target == null) {
      return;
    }

    PlayerInventory inv = target.getInventory();
    Location loc = target.getLocation();

    for (ItemStack item : inv.getContents()) {
      if (item != null) {
        target.getWorld().dropItemNaturally(loc, item.clone());
      }
    }
    for (ItemStack armor : inv.getArmorContents()) {
      if (armor != null) {
        target.getWorld().dropItemNaturally(loc, armor.clone());
      }
    }
    ItemStack off = inv.getItemInOffHand();
    target.getWorld().dropItemNaturally(loc, off.clone());

    inv.clear();

    player.sendMessage(
        Colors.color(Settings.LangKey.DROPINV_DROPPED.get(), "%player%", args[1]));
  }

  @Override
  public String getPermission() {
    return "epictroll.dropinv";
  }

  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }
    return null;
  }
}