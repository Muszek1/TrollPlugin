package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
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

		if (player.hasPermission("epictroll.knockbackstick")) {
			ItemStack knockbackStick = new ItemStack(Material.STICK);
			ItemMeta meta = knockbackStick.getItemMeta();
			meta.addEnchant(Enchantment.KNOCKBACK, 10, true);
			meta.setDisplayName(Colors.color(Settings.Knockback.KNOCKBACK_ITEM_NAME));
			knockbackStick.setItemMeta(meta);
			if (args.length == 0) {

				player.getInventory().addItem(knockbackStick);
			}
			if (args.length > 0) {

				Player target = Bukkit.getPlayer(args[1]);
				target.getInventory().addItem(knockbackStick);

			}
		} else {
			player.sendMessage(Colors.color(Settings.NO_PERMISSION));
		}

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
