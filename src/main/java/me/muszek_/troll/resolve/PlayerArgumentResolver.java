package me.muszek_.troll.resolve;

import dev.rollczi.litecommands.argument.Argument;
import dev.rollczi.litecommands.argument.parser.ParseResult;
import dev.rollczi.litecommands.argument.resolver.ArgumentResolver;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.suggestion.SuggestionContext;
import dev.rollczi.litecommands.suggestion.SuggestionResult;
import me.muszek_.troll.config.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerArgumentResolver extends ArgumentResolver<CommandSender, Player> {

    private final MessageConfig messageConfig;

    public PlayerArgumentResolver(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    @Override
    protected ParseResult<Player> parse(Invocation<CommandSender> invocation, Argument<Player> context, String argument) {
        Player player = Bukkit.getPlayer(argument);
        if (player == null) {
            String message = messageConfig.Player_Not_Found.replace("%player%", argument);
            return ParseResult.failure(message);
        }
        return ParseResult.success(player);
    }

    @Override
    public SuggestionResult suggest(Invocation<CommandSender> invocation, Argument<Player> argument, SuggestionContext context) {
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .collect(SuggestionResult.collector());
    }
}