package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TrollCommandAnvil extends SubCommand {
	@Override
	public String getName() {
		return "anvil";
	}

	@Override
	public String getDescription() {
		return "Spawns an Anvil above the player";
	}

	@Override
	public String getSyntax() {
		return "/troll anvil <player>";
	}

	@Override
	public void perform(Player player, String[] args) {
		if (!player.hasPermission("epictroll.anvil")) {
			player.sendMessage(Colors.color(Settings.NO_PERMISSION));
			return;
		}

		if (args.length == 1) {
			player.sendMessage(Colors.color(Settings.Anvil.ANVIL_USAGE));
			return;
		}

		Player target = Bukkit.getPlayerExact(args[1]);
		if (target == null) {
			player.sendMessage(Colors.color(Settings.PLAYER_NOT_FOUND.replace("%player%", args[0])));
			return;
		}

		player.sendMessage(Colors.color(Settings.Anvil.ANVIL_MESSAGE.replace("%player%", target.getName())));

		Block block = target.getLocation().getBlock();
		Block blockAbove = block.getRelative(0, 5, 0);

		if (blockAbove.getType() != Material.AIR) {
			player.sendMessage(Colors.color(Settings.Anvil.ANVIL_ERROR));
			return;
		}

		blockAbove.setType(Material.ANVIL);

	}


	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
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
