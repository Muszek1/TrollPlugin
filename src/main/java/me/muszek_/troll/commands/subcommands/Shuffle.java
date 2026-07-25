package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Command(name = "troll shuffle")
@Permission("epictroll.shuffle")
public class Shuffle {

  private final Troll plugin;

  public Shuffle(Troll plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {
    ItemStack[] contents = target.getInventory().getContents();
    List<ItemStack> items = new ArrayList<>(Arrays.asList(contents));
    Collections.shuffle(items);
    target.getInventory().setContents(items.toArray(new ItemStack[0]));

    sender.sendMessage(Colors.color(plugin.getMessageConfig().Shuffle.Sent, "%player%", target.getName()));
  }
}