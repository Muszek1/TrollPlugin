package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import org.bukkit.command.CommandSender;

@Command(name = "troll reload")
@Permission("epictroll.reload")
public class Reload {

  private final Troll plugin;

  public Reload(Troll plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context CommandSender sender) {
    plugin.getPluginConfig().load();
    plugin.getMessageConfig().load();
    sender.sendMessage(Colors.color(plugin.getMessageConfig().Plugin_Reloaded));
  }
}