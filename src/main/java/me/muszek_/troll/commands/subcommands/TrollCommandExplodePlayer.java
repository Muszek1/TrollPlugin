package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class TrollCommandExplodePlayer extends SubCommand {
	@Override
	public String getName() {
		return "explode";
	}

	@Override
	public String getDescription() {
		return "explodes a player";
	}

	@Override
	public String getSyntax() {
		return "/troll explode <player>";
	}

	@Override
	public void perform(Player player, String[] args) {

		if (player.hasPermission("epictroll.explode")) {
			if (args.length == 2) {
				if (Bukkit.getPlayerExact(args[1]) == null) {
					player.sendMessage(Colors.color(Settings.PLAYER_NOT_FOUND.replace("%player%", args[1])));
					return;
				}
				Player target = Bukkit.getPlayerExact(args[1]);
				target.sendMessage(Colors.color(Settings.ExplodePlayer.EXPLODEPLAYER_GOING_TO_EXPLODE));
				player.sendMessage(Colors.color(Settings.ExplodePlayer.EXPLODEPLAYER_MESSAGE).replace("%player%", args[1]));
				new BukkitRunnable() {
					int count = 0;

					@Override
					public void run() {
						if (count < 3) {
							target.playSound(target.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1, 1);
							count++;
						} else {
							target.playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
							target.setHealth(0);
							target.sendMessage(Colors.color(Settings.ExplodePlayer.EXPLODEPLAYER_YOU_WERE_BLOWN_UP));
							cancel();
						}
					}
				}.runTaskTimer(Troll.getInstance(), 0L, 15L);
			} else {
				player.sendMessage(Colors.color(Settings.ExplodePlayer.EXPLODEPLAYER_USAGE));
			}
		} else {
			player.sendMessage(Colors.color(Settings.NO_PERMISSION));
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
