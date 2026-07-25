package me.muszek_.troll.resolve;

import dev.rollczi.litecommands.argument.Argument;
import dev.rollczi.litecommands.argument.parser.ParseResult;
import dev.rollczi.litecommands.argument.resolver.ArgumentResolver;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.suggestion.SuggestionContext;
import dev.rollczi.litecommands.suggestion.SuggestionResult;
import me.muszek_.troll.config.MessageConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

import java.util.Locale;

public class EntityTypeArgumentResolver extends ArgumentResolver<CommandSender, EntityType> {

    private final MessageConfig messageConfig;

    public EntityTypeArgumentResolver(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    @Override
    protected ParseResult<EntityType> parse(Invocation<CommandSender> invocation, Argument<EntityType> context, String argument) {
        try {
            EntityType entityType = EntityType.valueOf(argument.toUpperCase(Locale.ROOT));
            return ParseResult.success(entityType);
        } catch (IllegalArgumentException exception) {
            String formattedMessage = messageConfig.Mob_Not_Found.replace("%mob%", argument);

            return ParseResult.failure(formattedMessage);
        }
    }

    @Override
    public SuggestionResult suggest(Invocation<CommandSender> invocation, Argument<EntityType> argument, SuggestionContext context) {
        return java.util.Arrays.stream(EntityType.values())
                .filter(EntityType::isSpawnable)
                .map(type -> type.name().toLowerCase(java.util.Locale.ROOT))
                .collect(SuggestionResult.collector());
    }
}