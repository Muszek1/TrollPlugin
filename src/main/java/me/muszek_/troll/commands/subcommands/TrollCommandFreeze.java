package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TrollCommandFreeze extends SubCommand {
	@Override
	public String getName() {
		return "freeze";
	}

	@Override
	public String getDescription() {
		return "freeze a player";
	}

	@Override
	public String getSyntax() {
		return "/troll freeze <player>";
	}

	@Override
	public void perform(Player player, String[] args) {

		if (player.hasPermission("troll.freeze")) {
			if (args.length == 1) {
				player.sendMessage(Colors.color(Settings.Freeze.FREEZE_USAGE));
				return;
			}
			if (args.length > 1) {
				Player target = Bukkit.getPlayer(args[1]);
				player.sendMessage(Colors.color(Settings.Freeze.FREEZE_MESSAGE));
				int time;
				try {
					time = Integer.parseInt(args[2]);
				} catch (NumberFormatException ex) {
					player.sendMessage(Colors.color(Settings.Freeze.FREEZE_INVALID_DURATION));
					return;
				}
				if (time <= 0) {
					player.sendMessage(Colors.color(Settings.Freeze.FREEZE_INVALID_DURATION));
					return;
				}
				target.setFreezeTicks(time * 20);
				target.sendMessage(Colors.color(Settings.Freeze.FREEZE_TARGET_MESSAGE.replace("%player%", args[1])));
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
