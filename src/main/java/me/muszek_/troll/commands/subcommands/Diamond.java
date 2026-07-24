package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

@Command(name = "troll diamond")
@Permission("epictroll.diamond")
public class Diamond {


  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {

    Location loc = target.getLocation().add(0, 1, 0);
    ItemStack diamond = new ItemStack(Material.DIAMOND);
    ItemMeta meta = diamond.getItemMeta();

    NamespacedKey key = new NamespacedKey("troll", "diamond");
    meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
    diamond.setItemMeta(meta);

    Item item = target.getWorld().dropItem(loc, diamond);

    Bukkit.getScheduler()
        .runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("EpicTroll")),
            item::remove, (20L * (int) Settings.ConfigKey.DIAMOND_DURATION.get()));

    sender.sendMessage(
        Colors.color((Settings.LangKey.DIAMOND_GIVEN.get()).replace("%player%", target.getName())));
  }
}
