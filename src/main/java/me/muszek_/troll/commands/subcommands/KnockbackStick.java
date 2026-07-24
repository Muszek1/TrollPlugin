package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.optional.OptionalArg;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Command(name = "troll knockbackstick")
@Permission("epictroll.knockbackstick")
public class KnockbackStick {

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target, @OptionalArg("number") Integer number) {

    Integer amount = number;
    if (amount == null) {
      amount = 1;
    }
    ItemStack knockbackStick = new ItemStack(Material.STICK, amount);
    ItemMeta meta = knockbackStick.getItemMeta();
    meta.addEnchant(Enchantment.KNOCKBACK, 10, true);
    meta.displayName((Colors.color(Settings.ConfigKey.KNOCKBACK_ITEM_NAME.get())));
    knockbackStick.setItemMeta(meta);

    target.getInventory().addItem(knockbackStick);
    sender.sendMessage(
        Colors.color(Settings.LangKey.KNOCKBACK_GIVEN.get(), "%player%", target.getName(),
            "%amount%", String.valueOf(amount)));
  }

}
