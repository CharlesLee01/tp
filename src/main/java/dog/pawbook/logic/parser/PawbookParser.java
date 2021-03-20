package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static dog.pawbook.commons.core.Messages.MESSGAE_UNKNOWN_ENTITY;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dog.pawbook.logic.commands.AddCommand;
import dog.pawbook.logic.commands.AddOwnerCommand;
import dog.pawbook.logic.commands.Command;
import dog.pawbook.logic.commands.DeleteCommand;
import dog.pawbook.logic.commands.DeleteOwnerCommand;
import dog.pawbook.logic.commands.ExitCommand;
import dog.pawbook.logic.commands.HelpCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class PawbookParser {

    /**
     * Used for initial separation of command word, entity type, and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+) *(?<entityType>(?:owner|program|dog))?(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        final String entityType = Optional.ofNullable(matcher.group("entityType")).orElse("");

        switch (commandWord) {

        case AddOwnerCommand.COMMAND_WORD:
            return generateAddCommand(entityType, arguments);

        case DeleteOwnerCommand.COMMAND_WORD:
            return generateDeleteCommand(entityType, arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }


    }

    private AddCommand generateAddCommand(String entityType, String arguments) throws ParseException {
        if (entityType.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        switch (entityType) {
        case AddOwnerCommand.ENTITY_WORD:
            return new AddOwnerCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSGAE_UNKNOWN_ENTITY);
        }
    }

    private DeleteCommand generateDeleteCommand(String entityType, String arguments) throws ParseException {
        if (entityType.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        switch (entityType) {
        case DeleteOwnerCommand.ENTITY_WORD:
            return new DeleteOwnerCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSGAE_UNKNOWN_ENTITY);
        }
    }
}
