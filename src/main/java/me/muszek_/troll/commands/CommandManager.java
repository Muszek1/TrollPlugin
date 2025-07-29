package me.muszek_.troll.commands;

import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.subcommands.*;
import me.muszek_.troll.listeners.BlockCraftListener;
import me.muszek_.troll.listeners.BlockToolUseListener;
import me.muszek_.troll.listeners.JumplockListener;
import me.muszek_.troll.listeners.ReverseChatListener;
import me.muszek_.troll.settings.Settings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CommandManager implements CommandExecutor, TabCompleter {

	private final ArrayList<SubCommand> subCommands = new ArrayList<>();


	public CommandManager(JavaPlugin plugin, BlockToolUseListener BlockToolUseListener, JumplockListener jumplockListener, BlockCraftListener BlockCraftListener, ReverseChatListener ReversedChatListener) {
		subCommands.add(new TrollCommandAnvil());
		subCommands.add(new TrollCommandFire());
		subCommands.add(new TrollCommandMob());
		subCommands.add(new TrollCommandHelp());
		subCommands.add(new TrollCommandKnockbackStick());
		subCommands.add(new TrollCommandFreeze());
		subCommands.add(new TrollCommandExplodePlayer());
		subCommands.add(new TrollCommandReload());
		subCommands.add(new TrollCommandApple());
		subCommands.add(new TrollCommandDiamond());
		subCommands.add(new TrollCommandLaunch());
		subCommands.add(new TrollCommandCookie());
		subCommands.add(new TrollCommandFakeOp());
		subCommands.add(new TrollCommandAnnoySounds());
		subCommands.add(new TrollCommandDropInv());
		subCommands.add(new TrollCommandGui(plugin));
		subCommands.add(new TrollCommandBlockToolUse(BlockToolUseListener));
		subCommands.add(new TrollCommandBlockCraft(BlockCraftListener));
		subCommands.add(new TrollCommandJumplock(jumplockListener));
		subCommands.add(new TrollCommandReverseChat(ReversedChatListener));
	}


	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (sender instanceof Player player) {
			if (args.length > 0) {
				for (SubCommand subCommand : getSubCommands()) {
					if (args[0].equalsIgnoreCase(subCommand.getName())) {

						if (!player.hasPermission(subCommand.getPermission())) {
							player.sendMessage(Colors.color(Settings.LangKey.NO_PERMISSION.get()));
							return true;
						}

						subCommand.perform(player, args);
						return true;
					}
				}
				player.sendMessage(Colors.color("&cNieznana podkomenda. Użyj /troll help."));
			} else {
				player.sendMessage(Colors.color("Usage: /troll help"));
			}
		}
		return true;
	}

	public ArrayList<SubCommand> getSubCommands() {
		return subCommands;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

		if (args.length == 1) {
			ArrayList<String> subCommandsArguments = new ArrayList<>();

			for (int i = 0; i < getSubCommands().size(); i++) {
				subCommandsArguments.add(getSubCommands().get(i).getName());
			}
			return subCommandsArguments;
		} else if (args.length >= 2) {
			for (int i = 0; i < getSubCommands().size(); i++) {
				if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
					return getSubCommands().get(i).getSubcommandArguments((Player) sender, args);
				}
			}
		}


		return null;
	}
}
