package me.muszek_.troll.commands.subcommands;

import java.util.ArrayList;
import java.util.List;
import me.muszek_.troll.Colors;
import me.muszek_.troll.commands.SubCommand;
import me.muszek_.troll.settings.Settings;
import me.muszek_.troll.utils.TabCompletePlayer;
import me.muszek_.troll.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Mob extends SubCommand {


  @Override
  public String getName() {
    return "mob";
  }

  @Override
  public String getDescription() {
    return "spawns a mob behind a player";
  }

  @Override
  public String getSyntax() {
    return "/troll mob <player> <mob> <amount>";
  }

  @Override
  public void perform(Player player, String[] args) {

    if (args.length == 1 || args.length == 2) {
      player.sendMessage(Colors.color(Settings.LangKey.MOB_USAGE.get()));
      return;
    }

    Player target = Utils.getTarget(player, args[1]);
    if (target == null) {
      return;
    }

    EntityType mob;
    try {
      mob = EntityType.valueOf(args[2].toUpperCase());
    } catch (IllegalArgumentException e) {
      player.sendMessage(
          Colors.color(Settings.LangKey.MOB_NOT_FOUND.get()).replace("%mob%", args[2]));
      return;
    }

    Location playerLocation = target.getLocation();
    Location spawnLocation = playerLocation.clone()
        .add(playerLocation.getDirection().normalize().multiply(-0.9));
    int amount = 1;
    if (args.length >= 4) {
      try {
        amount = Integer.parseInt(args[3]);
      } catch (NumberFormatException e) {
        player.sendMessage(Colors.color(Settings.LangKey.WRONG_NUMBER.get()));
        return;
      }
    }
    for (int i = 0; i < amount; i++) {
      target.getWorld().spawnEntity(spawnLocation, mob);
    }

    assert mob.getName() != null;
    player.sendMessage(Colors.color(
        (Settings.LangKey.MOB_MESSAGE.get()).replace("%player%", target.getName())
            .replace("%mob%", mob.getName())));


  }

  @Override
  public String getPermission() {
    return "epictroll.mob";
  }

  @Override
  public List<String> getSubcommandArguments(Player player, String[] args) {
    if (args.length == 2) {
      return TabCompletePlayer.getOnlinePlayerNames();
    }
    if (args.length == 3) {
      List<String> mobList = new ArrayList<>();
      for (EntityType type : EntityType.values()) {

        if (type.getEntityClass() != null &&
            org.bukkit.entity.LivingEntity.class.isAssignableFrom(type.getEntityClass())) {
          mobList.add(type.name());
        }
      }

      return mobList;
    }
    return null;
  }


}


