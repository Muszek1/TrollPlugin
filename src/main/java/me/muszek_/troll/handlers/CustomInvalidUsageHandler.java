package me.muszek_.troll.handlers;

import dev.rollczi.litecommands.invalidusage.InvalidUsage;
import dev.rollczi.litecommands.invalidusage.InvalidUsageHandler;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.schematic.Schematic;
import me.muszek_.troll.Colors;
import me.muszek_.troll.config.MessageConfig;
import org.bukkit.command.CommandSender;

public class CustomInvalidUsageHandler implements InvalidUsageHandler<CommandSender> {

    private final MessageConfig messageConfig;

    public CustomInvalidUsageHandler(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    @Override
    public void handle(Invocation<CommandSender> invocation, InvalidUsage<CommandSender> result, dev.rollczi.litecommands.handler.result.ResultHandlerChain<CommandSender> chain) {
        CommandSender sender = invocation.sender();

        if (result.getCause() == InvalidUsage.Cause.INVALID_ARGUMENT) {
            sender.sendMessage(Colors.color(messageConfig.Wrong_Number));
            return;
        }

        String label = invocation.label();
        String[] args = invocation.arguments().asArray();

        if (args.length == 0) {
            sender.sendMessage(Colors.color(messageConfig.Invalid_Usage + " &e/" + label + " gui <player>"));
            return;
        }

        Schematic schematic = result.getSchematic();
        if (!schematic.all().isEmpty()) {
            sender.sendMessage(Colors.color(messageConfig.Invalid_Usage + " &e" + schematic.first()));
        }
    }
}