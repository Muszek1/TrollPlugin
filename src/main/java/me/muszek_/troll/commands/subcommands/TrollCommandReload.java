package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import org.bukkit.entity.Player;

import java.util.List;

public class TrollCommandReload extends SubCommand {

	@Override
	public String getName() {
		return "reload";
	}

	@Override
	public String getDescription() {
		return "reloads the plugin";
	}

	@Override
	public String getSyntax() {
		return "/troll reload";
	}

	@Override
	public void perform(Player player, String[] args) {

		if (player.hasPermission("epictroll.reload")) {
			Troll.getInstance().reloadConfig();
			Settings.load();
			player.sendMessage(Colors.color(Settings.PLUGIN_RELOADED));
		} else {
			player.sendMessage(Colors.color(Settings.NO_PERMISSION));
		}
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		return null;
	}
}
