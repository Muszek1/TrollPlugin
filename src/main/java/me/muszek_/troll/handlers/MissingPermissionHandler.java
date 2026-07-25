package me.muszek_.troll.handlers;

import dev.rollczi.litecommands.handler.result.ResultHandler;
import dev.rollczi.litecommands.handler.result.ResultHandlerChain;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.permission.MissingPermissions;
import me.muszek_.troll.config.MessageConfig;
import org.bukkit.command.CommandSender;

public class MissingPermissionHandler implements ResultHandler<CommandSender, MissingPermissions> {

    private final MessageConfig messageConfig;

    public MissingPermissionHandler(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    @Override
    public void handle(Invocation<CommandSender> invocation, MissingPermissions exception, ResultHandlerChain<CommandSender> chain) {
        CommandSender sender = invocation.sender();
        sender.sendMessage(messageConfig.No_Permissions);
    }
}