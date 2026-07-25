package me.muszek_.troll.commands.subcommands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.muszek_.troll.Colors;
import me.muszek_.troll.Troll;
import me.muszek_.troll.menusystem.PlayerMenuUtility;
import me.muszek_.troll.menusystem.menu.GuiCommandMenu;
import org.bukkit.entity.Player;

@Command(name = "troll")
public class MainCommand {

    private final Troll plugin;

    public MainCommand(Troll plugin) {
        this.plugin = plugin;
    }

    @Execute
    @Permission("epictroll.gui")
    public void executeRoot(@Context Player sender) {
        sender.sendMessage(Colors.color("&c> &fUsage: &e/troll gui <player>"));
    }

    @Execute
    @Permission("epictroll.gui")
    public void executeRoot(@Context Player sender, @Arg("player") Player target) {
        PlayerMenuUtility playerMenuUtility = Troll.getPlayerMenuUtility(sender);
        playerMenuUtility.setTarget(target);
        new GuiCommandMenu(playerMenuUtility, plugin).open();
    }
}