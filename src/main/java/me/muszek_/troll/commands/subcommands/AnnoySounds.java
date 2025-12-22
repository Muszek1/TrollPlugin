package me.muszek_.troll.commands.subcommands;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.tasks.AnnoySoundsTask;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AnnoySounds extends SubCommand {

  private final Random random = new Random();
  private final List<Sound> annoyingSounds = Arrays.asList(
      Sound.ENTITY_VILLAGER_NO,
      Sound.ENTITY_ENDERMAN_SCREAM,
      Sound.ENTITY_ITEM_BREAK,
      Sound.ENTITY_CREEPER_PRIMED,
      Sound.ENTITY_ELDER_GUARDIAN_CURSE,
      Sound.BLOCK_NOTE_BLOCK_BASS,
      Sound.BLOCK_BELL_USE
  );

  @Override
  public String getName() {
    return "annoysounds";
  }

  @Override
  public String getDescription() {
    return "plays annoying sounds for the player";
  }

  @Override
  public String getSyntax() {
    return "/troll annoysounds <player>";
  }

  @Override
  public void perform(Player player, String[] args) {
    if (args.length == 1) {
      player.sendMessage(Colors.color(Settings.LangKey.ANNOYSOUNDS_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(player, args[1]);
    if (target == null) {
      return;
    }

    player.sendMessage(
        Colors.color(Settings.LangKey.ANNOYSOUNDS_SENT.get()).replace("%player%", args[1]));

    new AnnoySoundsTask(target, annoyingSounds)
        .runTaskTimer(getPlugin(), 0L, 10L);
  }

  @Override
  public String getPermission() {
    return "epictroll.annoysounds";
  }

  private org.bukkit.plugin.Plugin getPlugin() {
    return org.bukkit.Bukkit.getPluginManager().getPlugin("EpicTroll");
  }

  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }

    return null;
  }
}