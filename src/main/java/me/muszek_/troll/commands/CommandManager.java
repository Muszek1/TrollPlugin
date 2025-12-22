package me.muszek_.troll.commands;

import java.util.ArrayList;
import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.subcommands.AnnoySounds;
import me.muszek_.troll.commands.subcommands.Anvil;
import me.muszek_.troll.commands.subcommands.Apple;
import me.muszek_.troll.commands.subcommands.BlockCraft;
import me.muszek_.troll.commands.subcommands.BlockToolUse;
import me.muszek_.troll.commands.subcommands.Cookie;
import me.muszek_.troll.commands.subcommands.Diamond;
import me.muszek_.troll.commands.subcommands.DropInv;
import me.muszek_.troll.commands.subcommands.ExplodePlayer;
import me.muszek_.troll.commands.subcommands.FakeOp;
import me.muszek_.troll.commands.subcommands.FakeXp;
import me.muszek_.troll.commands.subcommands.Fire;
import me.muszek_.troll.commands.subcommands.Freeze;
import me.muszek_.troll.commands.subcommands.Gui;
import me.muszek_.troll.commands.subcommands.Help;
import me.muszek_.troll.commands.subcommands.Jumplock;
import me.muszek_.troll.commands.subcommands.KnockbackStick;
import me.muszek_.troll.commands.subcommands.Launch;
import me.muszek_.troll.commands.subcommands.Mob;
import me.muszek_.troll.commands.subcommands.Reload;
import me.muszek_.troll.commands.subcommands.ReverseChat;
import me.muszek_.troll.commands.subcommands.Shuffle;
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


public class CommandManager implements CommandExecutor, TabCompleter {

  private final ArrayList<SubCommand> subCommands = new ArrayList<>();


  public CommandManager(JavaPlugin plugin, BlockToolUseListener BlockToolUseListener,
      JumplockListener jumplockListener, BlockCraftListener BlockCraftListener,
      ReverseChatListener ReversedChatListener) {
    subCommands.add(new Anvil());
    subCommands.add(new Fire());
    subCommands.add(new Mob());
    subCommands.add(new Help());
    subCommands.add(new KnockbackStick());
    subCommands.add(new Freeze());
    subCommands.add(new ExplodePlayer());
    subCommands.add(new Reload());
    subCommands.add(new Apple());
    subCommands.add(new Diamond());
    subCommands.add(new Launch());
    subCommands.add(new Cookie());
    subCommands.add(new FakeXp(plugin));
    subCommands.add(new FakeOp());
    subCommands.add(new AnnoySounds());
    subCommands.add(new DropInv());
    subCommands.add(new Shuffle());
    subCommands.add(new Gui(plugin));
    subCommands.add(new BlockToolUse(BlockToolUseListener));
    subCommands.add(new BlockCraft(BlockCraftListener));
    subCommands.add(new Jumplock(jumplockListener));
    subCommands.add(new ReverseChat(ReversedChatListener));
  }


  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
      @NotNull String label, @NotNull String[] args) {
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
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
      @NotNull Command command, @NotNull String label, @NotNull String[] args) {

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
