package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

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


		Player target = player;
		int amount = 1;

		if (args.length >= 2) {
			Player found = Bukkit.getPlayer(args[1]);
			if (found != null) {
				target = found;
			} else {
				player.sendMessage(Colors.color(Settings.LangKey.PLAYER_NOT_FOUND.get()).replace("%player%", args[1]));
				return;
			}
		}

		if (args.length >= 3) {
			try {
				amount = Integer.parseInt(args[2]);
				if (amount <= 0) amount = 1;
			} catch (NumberFormatException e) {
				player.sendMessage(Colors.color(Settings.LangKey.WRONG_NUMBER.get()));
				return;
			}
		}
		ItemStack cookie = new ItemStack(Material.COOKIE, amount);
		ItemMeta metaCookie = cookie.getItemMeta();
		NamespacedKey key = new NamespacedKey("troll", "cookie");
		metaCookie.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
		metaCookie.setDisplayName(Colors.color(Settings.ConfigKey.COOKIE_ITEM_NAME.get()));
		if (Settings.ConfigKey.COOKIE_GLOW.get()) {
			metaCookie.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			metaCookie.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		cookie.setItemMeta(metaCookie);
		target.getInventory().addItem(cookie);
		player.sendMessage(Colors.color(Settings.LangKey.COOKIE_GIVEN.get()).replace("%player%", target.getName()).replace("%amount%", String.valueOf(amount)));

	}

	@Override
	public String getPermission() {
		return "epictroll.cookie";
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
			return TabCompletePlayer.getOnlinePlayerNames();
		}
		return null;
	}
}
