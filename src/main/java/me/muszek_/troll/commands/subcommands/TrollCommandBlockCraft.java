package me.muszek_.troll.commands.subcommands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.listeners.BlockCraftListener;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class TrollCommandBlockCraft extends SubCommand {

	private final BlockCraftListener listener;

	public TrollCommandBlockCraft(BlockCraftListener listener) {
		this.listener = listener;
	}


	@Override
	public String getName() {
		return "blockcraft";
	}

	@Override
	public String getDescription() {
		return "blocks the possibility of crafting";
	}

	@Override
	public String getSyntax() {
		return "/troll blockcraft <player>";
	}

	@Override
	public void perform(Player player, String[] args) {

		if (!player.hasPermission("epictroll.blockcraft")) {
			player.sendMessage(Colors.color(Settings.LangKey.NO_PERMISSION.get()));
			return;
		}
		if (args.length == 1) {
			player.sendMessage(Colors.color(Settings.LangKey.BLOCKCRAFT_USAGE.get()));
			return;
		}

		Player target = Bukkit.getPlayerExact(args[1]);
		if (target == null) {
			player.sendMessage(Colors.color(Settings.LangKey.PLAYER_NOT_FOUND.get().replace("%player%", args[0])));
			return;
		}
		if (listener.isLocked(target)) {
			listener.unlock(target);
			player.sendMessage(Colors.color(Settings.LangKey.BLOCKCRAFT_UNBLOCK.get()).replace("%player%", args[1]));
		} else {
			listener.lock(target);
			player.sendMessage(Colors.color(Settings.LangKey.BLOCKCRAFT_BLOCK.get()).replace("%player%", args[1]));
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

