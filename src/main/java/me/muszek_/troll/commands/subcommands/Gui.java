package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Troll;
import me.muszek_.troll.menusystem.PlayerMenuUtility;
import me.muszek_.troll.menusystem.menu.GuiCommandMenu;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@Command(name = "troll gui")
@Permission("epictroll.gui")
public class Gui{

  private final JavaPlugin plugin;

  public Gui(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @Execute
  public void execute(@Context Player sender, @Arg("player") Player target) {

    PlayerMenuUtility playerMenuUtility = Troll.getPlayerMenuUtility(sender);
    playerMenuUtility.setTarget(target);

    new GuiCommandMenu(playerMenuUtility, plugin).open();
  }
}
