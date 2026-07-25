package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.optional.OptionalArg;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Command(name = "troll knockbackstick")
@Permission("epictroll.knockbackstick")
public class KnockbackStick {

  private final Troll plugin;

  public KnockbackStick(Troll plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target, @OptionalArg("number") Integer number) {
    int amount = (number == null || number <= 0) ? 1 : number;

    ItemStack knockbackStick = new ItemStack(Material.STICK, amount);
    ItemMeta meta = knockbackStick.getItemMeta();
    if (meta != null) {
      meta.addEnchant(Enchantment.KNOCKBACK, 10, true);
      meta.displayName(Colors.color(plugin.getPluginConfig().Knockback.Item_Name));
      knockbackStick.setItemMeta(meta);
    }

    target.getInventory().addItem(knockbackStick);
    sender.sendMessage(Colors.color(plugin.getMessageConfig().Knockback.Given, "%player%", target.getName(), "%amount%", String.valueOf(amount)));
  }
}