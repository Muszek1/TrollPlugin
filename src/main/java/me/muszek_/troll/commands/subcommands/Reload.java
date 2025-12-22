package me.muszek_.troll.commands.subcommands;

import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import org.bukkit.entity.Player;

public class Reload extends SubCommand {

  @Override
  public String getName() {
    return "reload";
  }

  @Override
  public String getDescription() {
    return "reloads the plugin";
  }

  @Override
  public String getSyntax() {
    return "/troll reload";
  }

  @Override
  public void perform(Player player, String[] args) {

    Troll.getInstance().reloadConfig();
    Settings.load();
    player.sendMessage(Colors.color(Settings.LangKey.PLUGIN_RELOADED.get()));

  }

  @Override
  public String getPermission() {
    return "epictroll.reload";
  }

  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    return null;
  }
}
