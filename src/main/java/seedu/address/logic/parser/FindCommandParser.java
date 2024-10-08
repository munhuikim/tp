package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClassIdContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution, n/ input finds for name, c/ input finds
     * for classId
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.contains("n/")) {
            String[] nameKeywords = parseName(trimmedArgs);
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }



        String[] classIdKeywords = parseClassId(trimmedArgs);
        return new FindCommand(new ClassIdContainsKeywordsPredicate(Arrays.asList(classIdKeywords)));




    }

    private String[] parseName(String args) throws ParseException {
        String[] names = args.split("n/", 2);
        if (names.length < 2 || names[1].trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return names[1].trim().split("\\s+");

    }

    private String[] parseClassId(String args) throws ParseException {
        String[] classIds = args.split("c/", 2);
        if (classIds.length < 2 || classIds[1].trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return classIds[1].trim().split("\\s+");

    }



}
