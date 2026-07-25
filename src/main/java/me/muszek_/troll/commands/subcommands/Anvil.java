package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "troll anvil")
@Permission("epictroll.anvil")
public class Anvil {

  private final Troll plugin;

  public Anvil(Troll plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {
    sender.sendMessage(Colors.color(plugin.getMessageConfig().Anvil.Message, "%player%", target.getName()));

    Block block = target.getLocation().getBlock();
    Block blockAbove = block.getRelative(0, 5, 0);

    if (blockAbove.getType() != Material.AIR) {
      sender.sendMessage(Colors.color(plugin.getMessageConfig().Anvil.Error));
      return;
    }

    blockAbove.setType(Material.ANVIL);
  }
}