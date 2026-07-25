package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.optional.OptionalArg;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@Command(name = "troll mob")
@Permission("epictroll.mob")
public class Mob {

  private final Troll plugin;

  public Mob(Troll plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(
          @Context CommandSender sender,
          @Arg("player") Player target,
          @Arg("mob") EntityType mob,
          @OptionalArg("amount") Integer number) {

    int amount = (number == null || number <= 0) ? 1 : number;
    Location playerLocation = target.getLocation();
    Location spawnLocation = playerLocation.clone()
            .add(playerLocation.getDirection().normalize().multiply(-0.9));

    for (int i = 0; i < amount; i++) {
      target.getWorld().spawnEntity(spawnLocation, mob);
    }

    sender.sendMessage(Colors.color(plugin.getMessageConfig().Mob.Message, "%player%", target.getName(), "%mob%", mob.name()));
  }
}