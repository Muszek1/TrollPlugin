package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.listeners.JumplockListener;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class TrollCommandJumplock extends SubCommand {

	private final JumplockListener listener;

	public TrollCommandJumplock(JumplockListener listener) {
		this.listener = listener;
	}

	@Override
	public String getName() {
		return "jumplock";
	}

	@Override
	public String getDescription() {
		return "Blocks jumping for the target player.";
	}

	@Override
	public String getSyntax() {
		return "/troll jumplock <player>";
	}

	@Override
	public void perform(Player sender, String[] args) {
		if (!sender.hasPermission("epictroll.jumplock")) {
			sender.sendMessage(Colors.color(Settings.NO_PERMISSION));
			return;
		}


		if (args.length <= 1) {
			sender.sendMessage(Colors.color(Settings.Jumplock.JUMPLOCK_USAGE));
			return;
		}

		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			sender.sendMessage(Colors.color(Settings.PLAYER_NOT_FOUND.replace("%player%", args[1])));
			return;
		}

		if (listener.isLocked(target)) {
			listener.unlock(target);
			sender.sendMessage(Colors.color(Settings.Jumplock.JUMPLOCK_UNLOCK).replace("%player%", args[1]));
		} else {
			listener.lock(target);
			sender.sendMessage(Colors.color(Settings.Jumplock.JUMPLOCK_LOCK).replace("%player%", args[1]));
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
