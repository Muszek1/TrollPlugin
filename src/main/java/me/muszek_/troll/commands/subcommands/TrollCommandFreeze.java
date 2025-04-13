package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
		return "/troll freeze <player> <duration>";
	}

	@Override
	public void perform(Player player, String[] args) {

		if (!player.hasPermission("epictroll.freeze")) {
			player.sendMessage(Colors.color(Settings.NO_PERMISSION));
			return;
		}
		if (args.length <= 1) {
			player.sendMessage(Colors.color(Settings.Freeze.FREEZE_USAGE));
			return;
		}
		Player target = Bukkit.getPlayerExact(args[1]);
		if (target == null) {
			player.sendMessage(Colors.color(Settings.PLAYER_NOT_FOUND.replace("%player%", args[1])));
			return;
		}
		player.sendMessage(Colors.color("tutaj"));
		int time = 3;
		if (args.length >= 3) {
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
		}

		target.sendMessage(Colors.color(Settings.Freeze.FREEZE_TARGET_MESSAGE.replace("%player%", args[1])));
		player.sendMessage(Colors.color(Settings.Freeze.FREEZE_MESSAGE.replace("%player%", args[1]).replace("%time%", Integer.toString(time))));
		int finalTime = time;
		new BukkitRunnable() {
			int count = 0;

			@Override
			public void run() {
				if (count < finalTime) {
					target.setFreezeTicks(1000);
					count++;
				} else {
					cancel();
				}
			}
		}.runTaskTimer(Troll.getInstance(), 20L, 20L);
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
