package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.tasks.AnnoySoundsTask;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;


@Command(name = "troll annoysounds")
@Permission("epictroll.annoysounds")
public class AnnoySounds {

  private final Troll plugin;

  public AnnoySounds(Troll plugin) {
    this.plugin = plugin;
  }

  private final List<Sound> annoyingSounds = Arrays.asList(
      Sound.ENTITY_VILLAGER_NO,
      Sound.ENTITY_ENDERMAN_SCREAM,
      Sound.ENTITY_ITEM_BREAK,
      Sound.ENTITY_CREEPER_PRIMED,
      Sound.ENTITY_ELDER_GUARDIAN_CURSE,
      Sound.BLOCK_NOTE_BLOCK_BASS,
      Sound.BLOCK_BELL_USE
  );

  @Execute
  public void execute(@Context CommandSender sender, @Arg("player") Player target) {

    String message = this.plugin.getMessageConfig().Annoysounds.Sent
            .replace("%player%", target.getName());

    sender.sendMessage(Colors.color(message));

    new AnnoySoundsTask(target, annoyingSounds)
        .runTaskTimer(this.plugin, 0L, 10L);
  }
}