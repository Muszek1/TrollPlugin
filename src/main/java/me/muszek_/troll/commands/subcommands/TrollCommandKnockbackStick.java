package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class TrollCommandKnockbackStick extends SubCommand {

	@Override
	public String getName() {
		return "knockbackstick";
	}

	@Override
	public String getDescription() {
		return "gives you n knockback stick";
	}

	@Override
	public String getSyntax() {
		return "/troll knobackstick <player>";
	}

	@Override
	public void perform(Player player, String[] args) {

		if (!player.hasPermission("epictroll.knockbackstick")) {
			player.sendMessage(Colors.color(Settings.LangKey.NO_PERMISSION.get()));
			return;
		}

		Player target = player;
		if (args.length >= 2) {
			target = Bukkit.getPlayer(args[1]);
			if (target == null) {
				player.sendMessage(Colors.color(Settings.LangKey.PLAYER_NOT_FOUND.get().replace("%player%", args[1])));
				return;
			}

		}

		int amount = 1;

		if (args.length >= 3) {
			try {
				amount = Integer.parseInt(args[2]);
				if (amount <= 0) amount = 1;
			} catch (NumberFormatException e) {
				player.sendMessage(Colors.color(Settings.LangKey.WRONG_NUMBER.get()));
				return;
			}
		}


		ItemStack knockbackStick = new ItemStack(Material.STICK, amount);
		ItemMeta meta = knockbackStick.getItemMeta();
		meta.addEnchant(Enchantment.KNOCKBACK, 10, true);
		meta.setDisplayName(Colors.color(Settings.ConfigKey.KNOCKBACK_ITEM_NAME.get()));
		knockbackStick.setItemMeta(meta);

		target.getInventory().addItem(knockbackStick);


	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
			return TabCompletePlayer.getOnlinePlayerNames();
		}
		return null;
	}
}
