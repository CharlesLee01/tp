package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_BELL;
import static dog.pawbook.testutil.TypicalDogs.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalIndexes.ID_FIRST_DOG;
import static dog.pawbook.testutil.TypicalIndexes.ID_SECOND_DOG;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditDogCommand.EditDogDescriptor;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDogCommand.
 */
public class EditDogCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void equals() {
        final EditDogCommand standardCommand = new EditDogCommand(ID_FIRST_DOG, DESC_ASHER);

        // same values -> returns true
        EditDogDescriptor copyDescriptor = new EditDogDescriptor(DESC_ASHER);
        EditDogCommand commandWithSameValues = new EditDogCommand(ID_FIRST_DOG, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditDogCommand(ID_SECOND_DOG, DESC_ASHER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDogCommand(ID_FIRST_DOG, DESC_BELL)));
    }
}
