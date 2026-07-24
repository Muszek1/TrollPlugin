package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

@Command(name = "troll cookie")
@Permission("epictroll.cookie")
public class Cookie{

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target, @Arg("number") int number) {

    int amount = (number <= 0) ? 1 : number;

    ItemStack cookie = new ItemStack(Material.COOKIE, amount);
    ItemMeta metaCookie = cookie.getItemMeta();
    NamespacedKey key = new NamespacedKey("troll", "cookie");
    metaCookie.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
    metaCookie.displayName(Colors.color(Settings.ConfigKey.COOKIE_ITEM_NAME.get()));
    if (Settings.ConfigKey.COOKIE_GLOW.get()) {
      metaCookie.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
      metaCookie.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    }
    cookie.setItemMeta(metaCookie);
    target.getInventory().addItem(cookie);
    sender.sendMessage(
        Colors.color(Settings.LangKey.COOKIE_GIVEN.get(), "%player%", target.getName(), "%amount%",
            String.valueOf(amount)));

  }

}
