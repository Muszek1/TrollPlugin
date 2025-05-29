package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.menusystem.PlayerMenuUtility;
import me.muszek_.troll.menusystem.menu.GuiCommandMenu;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class TrollCommandGui extends SubCommand {

	private final JavaPlugin plugin;

	public TrollCommandGui(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "gui";
	}

	@Override
	public String getDescription() {
		return "open the troll GUI";
	}

	@Override
	public String getSyntax() {
		return "/troll gui <name>";
	}

	@Override
	public void perform(Player player, String[] args) {
		if (!(player instanceof Player)) {
			return;
		}
		if (!player.hasPermission("epictroll.gui")) {
			player.sendMessage(Colors.color(Settings.LangKey.NO_PERMISSION.get()));
			return;
		}
		if (args.length <= 1) {
			player.sendMessage(Colors.color(Settings.LangKey.GUI_USAGE.get()));
			return;
		}
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			player.sendMessage(Colors.color(Settings.LangKey.PLAYER_NOT_FOUND.get().replace("%player%", args[1])));
			return;
		}

		PlayerMenuUtility util = Troll.getPlayerMenuUtility(player);
		util.setTarget(target);

		new GuiCommandMenu(util, plugin).open();
	}


	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
			return TabCompletePlayer.getOnlinePlayerNames();
		}

		return null;
	}
}
