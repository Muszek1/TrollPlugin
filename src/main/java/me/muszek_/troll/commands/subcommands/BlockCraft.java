package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.listeners.BlockCraftListener;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "troll blockcraft")
@Permission("epictroll.blockcraft")
public class BlockCraft {

  private final BlockCraftListener listener;
  private final Troll plugin;

  public BlockCraft(BlockCraftListener listener, Troll plugin) {
    this.listener = listener;
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {
    if (listener.isLocked(target)) {
      listener.unlock(target);
      sender.sendMessage(Colors.color(plugin.getMessageConfig().Blockcraft.Unblock, "%player%", target.getName()));
    } else {
      listener.lock(target);
      sender.sendMessage(Colors.color(plugin.getMessageConfig().Blockcraft.Block, "%player%", target.getName()));
    }
  }
}