package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.listeners.BlockCraftListener;
import me.muszek_.troll.settings.Settings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "troll blockcraft")
@Permission("epictroll.blockcraft")
public class BlockCraft {

  private final BlockCraftListener listener;

  public BlockCraft(BlockCraftListener listener) {
    this.listener = listener;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {

    if (listener.isLocked(target)) {
      listener.unlock(target);
      sender.sendMessage(
              Colors.color(Settings.LangKey.BLOCKCRAFT_UNBLOCK.get(), "%player%", target.getName()));
    } else {
      listener.lock(target);
      sender.sendMessage(
              Colors.color(Settings.LangKey.BLOCKCRAFT_BLOCK.get(), "%player%", target.getName()));
    }

  }
}

