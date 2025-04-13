package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TrollCommandFire extends SubCommand {
	private static final Logger log = LoggerFactory.getLogger(TrollCommandFire.class);


	@Override
	public String getName() {
		return "fire";
	}

	@Override
	public String getDescription() {
		return "Fire a player";
	}

	@Override
	public String getSyntax() {
		return "/troll fire <player>";
	}

	@Override
	public void perform(Player player, String[] args) {
		if (!(player instanceof Player)) {
			player.sendMessage("You must be a player to use this command!");
			return;
		}

		if (player.hasPermission("troll.fire")) {
			if (args.length == 1) {
				player.sendMessage(Colors.color(Settings.Fire.FIRE_USAGE));
				return;
			}
			if (args.length == 2) {
				if (Bukkit.getPlayerExact(args[1]) == null) {
					player.sendMessage(Colors.color(Settings.PLAYER_NOT_FOUND.replace("%player%", args[1])));
					return;
				}
				Player target = Bukkit.getPlayerExact(args[1]);
				int time = Settings.Fire.FIRE_DEFAULT_DURATION;
				player.sendMessage(Colors.color(Settings.PLAYER_NOT_FOUND.replace("%player%", args[1])));
				player.setFireTicks(time * 20);
			} else if (args.length == 3) {
				if (Bukkit.getPlayerExact(args[1]) == null) {
					player.sendMessage(Colors.color(Settings.PLAYER_NOT_FOUND.replace("%player%", args[1])));
					return;
				}
				int time;
				try {
					time = Integer.parseInt(args[2]);
				} catch (NumberFormatException ex) {
					player.sendMessage(Colors.color(Settings.Fire.FIRE_INVALID_DURATION));
					return;
				}
				if (time <= 0) {
					player.sendMessage(Colors.color(Settings.Fire.FIRE_INVALID_DURATION));
					return;
				}
				Player target = Bukkit.getPlayerExact(args[1]);
				player.sendMessage(Colors.color(Settings.Fire.FIRE_MESSAGE.replace("%player%", args[1]).replace("%time%", Integer.toString(time))));
				target.setFireTicks(20 * time);
			}
		} else {
			player.sendMessage(Colors.color(Settings.NO_PERMISSION));
		}
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
