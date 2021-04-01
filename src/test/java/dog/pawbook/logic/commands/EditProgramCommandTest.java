package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_PROGRAM_A;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_PROGRAM_B;
import static dog.pawbook.testutil.TypicalIndexes.ID_FIRST_PROGRAM;
import static dog.pawbook.testutil.TypicalIndexes.ID_SECOND_PROGRAM;
import static dog.pawbook.testutil.TypicalPrograms.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditProgramCommand.
 */
public class EditProgramCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        final EditProgramCommand standardCommand = new EditProgramCommand(ID_FIRST_PROGRAM, DESC_PROGRAM_A);

        // same values -> returns true
        EditProgramDescriptor copyDescriptor = new EditProgramDescriptor(DESC_PROGRAM_A);
        EditProgramCommand commandWithSameValues = new EditProgramCommand(ID_FIRST_PROGRAM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditProgramCommand(ID_SECOND_PROGRAM, DESC_PROGRAM_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditProgramCommand(ID_FIRST_PROGRAM, DESC_PROGRAM_B)));
    }
}
