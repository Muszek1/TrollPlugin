package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class TrollCommandCookie extends SubCommand {
	@Override
	public String getName() {
		return "cookie";
	}

	@Override
	public String getDescription() {
		return "Gives a player a infinite cookie";
	}

	@Override
	public String getSyntax() {
		return "/troll cookie <player> <amount>";
	}

	@Override
	public void perform(Player player, String[] args) {

		if (!player.hasPermission("epictroll.cookie")) {
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
		ItemStack cookie = new ItemStack(Material.COOKIE, amount);
		ItemMeta metaCookie = cookie.getItemMeta();
		NamespacedKey key = new NamespacedKey("troll", "cookie");
		metaCookie.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
		metaCookie.setDisplayName(Colors.color(Settings.Cookie.COOKIE_ITEM_NAME));
		if (Settings.Cookie.COOKIE_GLOW) {
			metaCookie.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			metaCookie.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		cookie.setItemMeta(metaCookie);
		target.getInventory().addItem(cookie);
		player.sendMessage(Colors.color(Settings.Cookie.COOKIE_GIVEN).replace("%player%", target.getName()).replace("%amount%", String.valueOf(amount)));

	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 1) {
			List<String> playerNames = new ArrayList<>();
			Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
			Bukkit.getServer().getOnlinePlayers().toArray(players);
			for (int i = 0; i < players.length; i++) {
				playerNames.add(players[i].getName());
			}

			return playerNames;


		}
		return null;
	}
}
