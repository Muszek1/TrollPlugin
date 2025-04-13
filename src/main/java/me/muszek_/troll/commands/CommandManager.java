package me.muszek_.troll.commands;

import me.muszek_.troll.commands.subcommands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CommandManager implements CommandExecutor, TabCompleter {

	private ArrayList<SubCommand> subCommands = new ArrayList<>();

	public CommandManager() {
		subCommands.add(new TrollCommandAnvil());
		subCommands.add(new TrollCommandFire());
		subCommands.add(new TrollCommandMob());
		subCommands.add(new TrollCommandHelp());
		subCommands.add(new TrollCommandKnockbackStick());
		subCommands.add(new TrollCommandFreeze());
		subCommands.add(new TrollCommandExplodePlayer());
		subCommands.add(new TrollCommandReload());
	}


	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;


			if (args.length > 0) {
				for (int i = 0; i < getSubCommands().size(); i++) {
					if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
						getSubCommands().get(i).perform(player, args);
					}
				}
			} else {
				player.sendMessage(ChatColor.RED + "Usage: /troll help");
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
