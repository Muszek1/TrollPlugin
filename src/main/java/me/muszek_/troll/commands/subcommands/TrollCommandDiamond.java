package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class TrollCommandDiamond extends SubCommand {

	@Override
	public String getName() {
		return "diamond";
	}

	@Override
	public String getDescription() {
		return "Spawns a diamond that can't be picked up.";
	}

	@Override
	public String getSyntax() {
		return "/troll diamond [player]";
	}

	@Override
	public void perform(Player sender, String[] args) {
		if (!sender.hasPermission("epictroll.diamond")) {
			sender.sendMessage(Colors.color(Settings.LangKey.NO_PERMISSION.get()));
			return;
		}

		Player target = sender;

		if (args.length >= 2) {
			target = Bukkit.getPlayer(args[1]);
			if (target == null) {
				sender.sendMessage(Colors.color(Settings.LangKey.PLAYER_NOT_FOUND.get().replace("%player%", args[1])));
				return;
			}
		}

		Location loc = target.getLocation().add(0, 1, 0);
		ItemStack diamond = new ItemStack(Material.DIAMOND);
		ItemMeta meta = diamond.getItemMeta();

		NamespacedKey key = new NamespacedKey("troll", "diamond");
		meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
		diamond.setItemMeta(meta);

		Item item = target.getWorld().dropItem(loc, diamond);

		Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("EpicTroll"), item::remove, (20L * (int) Settings.ConfigKey.DIAMOND_DURATION.get()));

		sender.sendMessage(Colors.color((Settings.LangKey.DIAMOND_GIVEN.get()).replace("%player%", target.getName())));
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
			return TabCompletePlayer.getOnlinePlayerNames();
		}

		return null;
	}
}
