package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.settings.Settings;
import org.bukkit.command.CommandSender;

@Command(name = "troll reload")
@Permission("epictroll.reload")
public class Reload{

  @Execute
  public void execute(@Context CommandSender sender) {

    Troll.getInstance().reloadConfig();
    Settings.load();
    sender.sendMessage(Colors.color(Settings.LangKey.PLUGIN_RELOADED.get()));

  }

}
