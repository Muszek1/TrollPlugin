package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

@Command(name = "troll apple")
@Permission("epictroll.apple")
public class Apple {

  private final Troll plugin;

  public Apple(Troll plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target, @Arg("number") int number) {
    int amount = (number <= 0) ? 1 : number;

    ItemStack apple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, amount);
    ItemMeta metaApple = apple.getItemMeta();

    if (metaApple != null) {
      NamespacedKey key = new NamespacedKey("troll", "apple");
      metaApple.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
      apple.setItemMeta(metaApple);
    }

    target.getInventory().addItem(apple);
    sender.sendMessage(Colors.color(plugin.getMessageConfig().Apple.Given, "%player%", target.getName(), "%amount%", String.valueOf(amount)));
  }
}