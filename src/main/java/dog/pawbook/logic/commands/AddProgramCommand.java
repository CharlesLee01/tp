package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SESSION;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;
import static dog.pawbook.model.managedentity.program.Program.ENTITY_WORD;

import dog.pawbook.model.managedentity.program.Program;

public class AddProgramCommand extends AddCommand<Program> {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds program to the database. \n"
            + "Parameters:\n"
            + "1) " + PREFIX_NAME + "PROGRAM_NAME "
            + PREFIX_SESSION + "SESSION "
            + "[" + PREFIX_SESSION + "SESSION]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example:\n"
            + "1) " + COMMAND_WORD + " " + ENTITY_WORD + " "
            + PREFIX_NAME + "Obedience Training "
            + PREFIX_SESSION + "01-02-2021 18:00 "
            + PREFIX_TAG + "puppies";

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_SUCCESS_FORMAT, ENTITY_WORD);
    public static final String MESSAGE_DUPLICATE_PROGRAM = "This " + ENTITY_WORD + " already exists in Pawbook.";

    /**
     * Creates an AddCommand to add the specified {@code program}
     */
    public AddProgramCommand(Program program) {
        super(program);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddProgramCommand // instanceof handles nulls
            && toAdd.equals(((AddProgramCommand) other).toAdd));
    }

    @Override
    protected String getSuccessMessage() {
        return MESSAGE_SUCCESS + toAdd;
    }

    @Override
    protected String getDuplicateMessage() {
        return MESSAGE_DUPLICATE_PROGRAM;
    }
}
