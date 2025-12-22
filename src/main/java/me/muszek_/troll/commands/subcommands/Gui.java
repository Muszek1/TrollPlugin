package me.muszek_.troll.commands.subcommands;

import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.menusystem.PlayerMenuUtility;
import me.muszek_.troll.menusystem.menu.GuiCommandMenu;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Gui extends SubCommand {

  private final JavaPlugin plugin;

  public Gui(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public String getName() {
    return "gui";
  }

  @Override
  public String getDescription() {
    return "open the troll GUI";
  }

  @Override
  public String getSyntax() {
    return "/troll gui <name>";
  }

  @Override
  public void perform(Player player, String[] args) {
    if (player == null) {
      return;
    }

    if (args.length <= 1) {
      player.sendMessage(Colors.color(Settings.LangKey.GUI_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(player, args[1]);
    if (target == null) {
      return;
    }

    PlayerMenuUtility playerMenuUtility = Troll.getPlayerMenuUtility(player);
    playerMenuUtility.setTarget(target);

    new GuiCommandMenu(playerMenuUtility, plugin).open();
  }

  @Override
  public String getPermission() {
    return "epictroll.gui";
  }


  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }

    return null;
  }
}
