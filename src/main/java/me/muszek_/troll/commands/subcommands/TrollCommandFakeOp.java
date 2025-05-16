package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class TrollCommandFakeOp extends SubCommand {
	@Override
	public String getName() {
		return "fakeop";
	}

	@Override
	public String getDescription() {
		return "sends fake message about receiving op";
	}

	@Override
	public String getSyntax() {
		return "/troll fakeop <player>";
	}

	@Override
	public void perform(Player player, String[] args) {

		if (!player.hasPermission("epictroll.fakeop")) {
			player.sendMessage(Colors.color(Settings.NO_PERMISSION));
			return;
		}
		if (args.length == 1) {
			player.sendMessage(Colors.color(Settings.FakeOp.FAKEOP_USAGE));
			return;
		}

		Player target = Bukkit.getPlayerExact(args[1]);
		if (target == null) {
			player.sendMessage(Colors.color(Settings.PLAYER_NOT_FOUND.replace("%player%", args[0])));
			return;
		}

		target.sendMessage(Colors.color(Settings.FakeOp.FAKEOP_MESSAGE_SENT).replace("%player%", args[0]));
		player.sendMessage(Colors.color(Settings.FakeOp.FAKEOP_MESSAGE_CONFIRMATION).replace("%player%", args[0]));


	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
			return TabCompletePlayer.getOnlinePlayerNames();
		}

		return null;
	}
}
