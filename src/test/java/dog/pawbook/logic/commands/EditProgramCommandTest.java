package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.*;
import static dog.pawbook.testutil.TypicalIndexes.*;
import static dog.pawbook.testutil.TypicalIndexes.ID_THIRD_DOG;
import static dog.pawbook.testutil.TypicalPrograms.getTypicalAddressBook;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.testutil.*;
import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.model.AddressBook;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.program.Program;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditProgramCommand.
 */
public class EditProgramCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Program editedProgram = new ProgramBuilder().build();
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder(editedProgram).build();
        EditProgramCommand editProgramCommand = new EditProgramCommand(ID_SECOND_PROGRAM, descriptor);

        String expectedMessage = String.format(EditProgramCommand.MESSAGE_EDIT_PROGRAM_SUCCESS, editedProgram);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEntity(ID_SECOND_PROGRAM, editedProgram);

        assertCommandSuccess(editProgramCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Program toEditProgram = (Program) model.getEntity(ID_THIRD_OWNER);

        ProgramBuilder programInList = new ProgramBuilder(toEditProgram);
        Program editedProgram = programInList.withName(VALID_NAME_PROGRAM2).withSessions(VALID_SESSION_PROGRAM2)
                .withTags(VALID_TAG_PUPPIES).build();

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withName(VALID_NAME_PROGRAM2)
                .withSessions(VALID_SESSION_PROGRAM2).withTags(VALID_TAG_PUPPIES).build();
        EditProgramCommand editEntityCommand = new EditProgramCommand(ID_THIRD_PROGRAM, descriptor);

        String expectedMessage = String.format(EditProgramCommand.MESSAGE_EDIT_PROGRAM_SUCCESS, editedProgram);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEntity(ID_THIRD_PROGRAM, editedProgram);

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditProgramCommand editEntityCommand = new EditProgramCommand(ID_SECOND_PROGRAM, new EditProgramDescriptor());

        Program editedProgram = (Program) model.getEntity(ID_SECOND_PROGRAM);

        String expectedMessage = String.format(EditProgramCommand.MESSAGE_EDIT_PROGRAM_SUCCESS, editedProgram);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateProgramUnfilteredList_failure() {
        Program firstProgram = (Program) model.getEntity(ID_FIRST_PROGRAM);
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder(firstProgram).build();
        EditProgramCommand editEntityCommand = new EditProgramCommand(ID_SECOND_PROGRAM, descriptor);

        assertCommandFailure(editEntityCommand, model, Messages.MESSAGE_DUPLICATE_PROGRAM);
    }

    @Test
    public void execute_invalidProgramIdUnfilteredList_failure() {
        Integer outOfBoundId = model.getUnfilteredEntityList().stream()
                .map(Pair::getKey).sorted().collect(toList()).get(model.getUnfilteredEntityList().size() - 1) + 1;
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withName(VALID_NAME_PROGRAM2).build();
        EditProgramCommand editEntityCommand = new EditProgramCommand(outOfBoundId, descriptor);

        assertCommandFailure(editEntityCommand, model, Messages.MESSAGE_INVALID_PROGRAM_ID);
    }

    @Test
    public void equals() {
        final EditProgramCommand standardCommand = new EditProgramCommand(ID_FIRST_PROGRAM, DESC_PROGRAM_1);

        // same values -> returns true
        EditProgramDescriptor copyDescriptor = new EditProgramDescriptor(DESC_PROGRAM_1);
        EditProgramCommand commandWithSameValues = new EditProgramCommand(ID_FIRST_PROGRAM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditProgramCommand(ID_SECOND_PROGRAM, DESC_PROGRAM_1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditProgramCommand(ID_FIRST_PROGRAM, DESC_PROGRAM_2)));
    }
}
