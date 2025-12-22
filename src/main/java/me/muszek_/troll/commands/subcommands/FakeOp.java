package me.muszek_.troll.commands.subcommands;

import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.entity.Player;

public class FakeOp extends SubCommand {

  @Override
  public String getName() {
    return "fakeop";
  }

  @Override
  public String getDescription() {
    return "sends fake message about receiving op";
  }

  @Override
  public String getSyntax() {
    return "/troll fakeop <player>";
  }

  @Override
  public void perform(Player player, String[] args) {

    if (args.length == 1) {
      player.sendMessage(Colors.color(Settings.LangKey.FAKEOP_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(player, args[1]);
    if (target == null) {
      return;
    }

    target.sendMessage(
        Colors.color(Settings.LangKey.FAKEOP_MESSAGE_SENT.get()).replace("%player%", args[0]));
    player.sendMessage(Colors.color(Settings.LangKey.FAKEOP_MESSAGE_CONFIRMATION.get())
        .replace("%player%", args[0]));


  }

  @Override
  public String getPermission() {
    return "epictroll.fakeop";
  }

  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }

    return null;
  }
}
