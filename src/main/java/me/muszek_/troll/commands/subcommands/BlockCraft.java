package me.muszek_.troll.commands.subcommands;

import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.listeners.BlockCraftListener;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.entity.Player;

public class BlockCraft extends SubCommand {

  private final BlockCraftListener listener;

  public BlockCraft(BlockCraftListener listener) {
    this.listener = listener;
  }


  @Override
  public String getName() {
    return "blockcraft";
  }

  @Override
  public String getDescription() {
    return "blocks the possibility of crafting";
  }

  @Override
  public String getSyntax() {
    return "/troll blockcraft <player>";
  }

  @Override
  public void perform(Player player, String[] args) {

    if (args.length == 1) {
      player.sendMessage(Colors.color(Settings.LangKey.BLOCKCRAFT_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(player, args[1]);
    if (target == null) {
      return;
    }

    if (listener.isLocked(target)) {
      listener.unlock(target);
      player.sendMessage(
          Colors.color(Settings.LangKey.BLOCKCRAFT_UNBLOCK.get(), "%player%", args[1]));
    } else {
      listener.lock(target);
      player.sendMessage(
          Colors.color(Settings.LangKey.BLOCKCRAFT_BLOCK.get(), "%player%", args[1]));
    }


  }

  @Override
  public String getPermission() {
    return "epictroll.blockcraft";
  }

  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }

    return null;
  }
}

