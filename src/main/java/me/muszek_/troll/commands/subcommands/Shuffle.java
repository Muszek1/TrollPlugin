package me.muszek_.troll.commands.subcommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Shuffle extends SubCommand {

  @Override
  public String getName() {
    return "shuffle";
  }

  @Override
  public String getDescription() {
    return "Shuffle player's inventory";
  }

  @Override
  public String getSyntax() {
    return "/troll shuffle <player>";
  }

  @Override
  public void perform(Player player, String[] args) {

    if (args.length == 1) {
      player.sendMessage(Colors.color(Settings.LangKey.SHUFFLE_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(player, args[1]);
    if (target == null) {
      return;
    }

    ItemStack[] contents = target.getInventory().getContents();
    List<ItemStack> items = new ArrayList<>(Arrays.asList(contents));
    Collections.shuffle(items);
    target.getInventory().setContents(items.toArray(new ItemStack[0]));

    player.sendMessage(
        Colors.color(Settings.LangKey.SHUFFLE_SENT.get()).replace("%player%", args[1]));

  }

  @Override
  public String getPermission() {
    return "epictroll.shuffle";
  }

  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }

    return null;
  }
}