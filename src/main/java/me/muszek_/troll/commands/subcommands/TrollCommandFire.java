package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

		if (!player.hasPermission("epictroll.fire")) {
			player.sendMessage(Colors.color(Settings.LangKey.NO_PERMISSION.get()));
			return;
		}

		if (args.length == 1) {
			player.sendMessage(Colors.color(Settings.LangKey.FIRE_USAGE.get()));
			return;
		}

		Player target = Bukkit.getPlayerExact(args[1]);
		if (target == null) {
			player.sendMessage(Colors.color(Settings.LangKey.PLAYER_NOT_FOUND.get().replace("%player%", args[0])));
			return;
		}
		if (args.length == 2) {
			int time = Settings.ConfigKey.FIRE_DEFAULT_DURATION.get();
			player.sendMessage(Colors.color(Settings.LangKey.FIRE_MESSAGE.get().replace("%player%", args[1]).replace("%time%", Integer.toString(time))));
			player.setFireTicks(time * 20);
		}

		if (args.length == 3) {
			int time = 3;
			try {
				time = Integer.parseInt(args[2]);
			} catch (NumberFormatException ex) {
				player.sendMessage(Colors.color(Settings.LangKey.FIRE_INVALID_DURATION.get()));
				return;
			}
			if (time <= 0) {
				player.sendMessage(Colors.color(Settings.LangKey.FIRE_INVALID_DURATION.get()));
				return;
			}
			player.sendMessage(Colors.color(Settings.LangKey.FIRE_MESSAGE.get().replace("%player%", args[1]).replace("%time%", Integer.toString(time))));
			target.setFireTicks(20 * time);
		}
	}


	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
			return TabCompletePlayer.getOnlinePlayerNames();
		}

		return null;
	}
}
