package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.listeners.BlockToolUseListener;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class TrollCommandBlockToolUse extends SubCommand {

	private final BlockToolUseListener listener;

	public TrollCommandBlockToolUse(BlockToolUseListener listener) {
		this.listener = listener;
	}

	@Override
	public String getName() {
		return "blocktooluse";
	}

	@Override
	public String getDescription() {
		return "block tool use";
	}

	@Override
	public String getSyntax() {
		return "/troll blocktooluse <player>";
	}

	@Override
	public void perform(Player sender, String[] args) {

		if (args.length == 1) {
			sender.sendMessage(Colors.color(Settings.LangKey.BLOCKTOOLUSE_USAGE.get()));
			return;
		}

		Player target = Bukkit.getPlayerExact(args[1]);
		if (target == null) {
			sender.sendMessage(Colors.color(Settings.LangKey.PLAYER_NOT_FOUND.get().replace("%player%", args[1])));
			return;
		}

		if (listener.isLocked(target)) {
			listener.unlock(target);
			sender.sendMessage(Colors.color(Settings.LangKey.BLOCKTOOLUSE_UNLOCK.get().replace("%player%", args[1])));
		} else {
			listener.lock(target);
			sender.sendMessage(Colors.color(Settings.LangKey.BLOCKTOOLUSE_BLOCK.get().replace("%player%", args[1])));
		}
	}

	@Override
	public String getPermission() {
		return "epictroll.blocktooluse";
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
			return TabCompletePlayer.getOnlinePlayerNames();
		}
		return null;
	}
}
