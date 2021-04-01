package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_BOB;
import static dog.pawbook.testutil.TypicalIndexes.ID_FIRST_OWNER;
import static dog.pawbook.testutil.TypicalIndexes.ID_SECOND_OWNER;
import static dog.pawbook.testutil.TypicalOwners.getTypicalDatabase;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditOwnerCommand.EditOwnerDescriptor;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditOwnerCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void equals() {
        final EditOwnerCommand standardCommand = new EditOwnerCommand(ID_FIRST_OWNER, DESC_AMY);

        // same values -> returns true
        EditOwnerDescriptor copyDescriptor = new EditOwnerDescriptor(DESC_AMY);
        EditOwnerCommand commandWithSameValues = new EditOwnerCommand(ID_FIRST_OWNER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditOwnerCommand(ID_SECOND_OWNER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditOwnerCommand(ID_FIRST_OWNER, DESC_BOB)));
    }

}
