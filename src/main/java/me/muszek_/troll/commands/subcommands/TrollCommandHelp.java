package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import org.bukkit.entity.Player;

import java.util.List;

public class TrollCommandHelp extends SubCommand {


	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getDescription() {
		return "displays the help message";
	}

	@Override
	public String getSyntax() {
		return "/troll help";
	}

	@Override
	public void perform(Player player, String[] args) {


		if (player.hasPermission("epictroll.help")) {

			player.sendMessage(Colors.color("&e> &fIf you need help, join to our discord: https://discord.gg/cT5MxqAYTd.\nList of commands are on spigot page."));
		} else {
			player.sendMessage(Colors.color(Settings.LangKey.NO_PERMISSION.get()));
		}
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		return List.of();
	}
}
