package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class TrollCommandApple extends SubCommand {
	@Override
	public String getName() {
		return "apple";
	}

	@Override
	public String getDescription() {
		return "gives you a gold poisoned apple";
	}

	@Override
	public String getSyntax() {
		return "/troll apple <player> <amount>";
	}

	@Override
	public void perform(Player player, String[] args) {

		if (!player.hasPermission("epictroll.apple")) {
			player.sendMessage(Colors.color(Settings.NO_PERMISSION));
		}
		Player target = player;
		int amount = 1;

		if (args.length >= 2) {
			Player found = Bukkit.getPlayer(args[1]);
			if (found != null) {
				target = found;
			} else {
				player.sendMessage(Colors.color(Settings.PLAYER_NOT_FOUND).replace("%player%", args[1]));
				return;
			}
		}

		if (args.length >= 3) {
			try {
				amount = Integer.parseInt(args[2]);
				if (amount <= 0) amount = 1;
			} catch (NumberFormatException e) {
				player.sendMessage(Colors.color(Settings.WRONG_NUMBER));
				return;
			}
		}
		ItemStack apple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, amount);
		ItemMeta metaApple = apple.getItemMeta();
		NamespacedKey key = new NamespacedKey("troll", "apple");
		metaApple.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
		apple.setItemMeta(metaApple);
		target.getInventory().addItem(apple);
		player.sendMessage(Colors.color(Settings.Apple.APPLE_GIVEN).replace("%player%", target.getName()).replace("%amount%", String.valueOf(amount)));

	}


	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
			return TabCompletePlayer.getOnlinePlayerNames();
		}
		return null;
	}
}
