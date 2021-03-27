package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_FRIENDLY;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalDogs.getTypicalAddressBook;
import static dog.pawbook.testutil.TypicalIndexes.ID_FIRST_DOG;
import static dog.pawbook.testutil.TypicalIndexes.ID_SECOND_DOG;
import static dog.pawbook.testutil.TypicalIndexes.ID_THIRD_DOG;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.logic.commands.EditDogCommand.EditDogDescriptor;
import dog.pawbook.model.AddressBook;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.testutil.DogBuilder;
import dog.pawbook.testutil.EditDogDescriptorBuilder;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDogCommand.
 */
public class EditDogCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Dog editedDog = new DogBuilder().build();
        EditDogDescriptor descriptor = new EditDogDescriptorBuilder(editedDog).build();
        EditDogCommand editDogCommand = new EditDogCommand(ID_SECOND_DOG, descriptor);

        String expectedMessage = String.format(EditDogCommand.MESSAGE_EDIT_DOG_SUCCESS, editedDog);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEntity(ID_SECOND_DOG, editedDog);

        assertCommandSuccess(editDogCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Dog toEditDog = (Dog) model.getEntity(ID_THIRD_DOG);

        DogBuilder dogInList = new DogBuilder(toEditDog);
        Dog editedDog = dogInList.withName(VALID_NAME_BELL).withBreed(VALID_BREED_BELL)
                .withTags(VALID_TAG_FRIENDLY).build();

        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withName(VALID_NAME_BELL)
                .withBreed(VALID_BREED_BELL).withTags(VALID_TAG_FRIENDLY).build();
        EditDogCommand editEntityCommand = new EditDogCommand(ID_THIRD_DOG, descriptor);

        String expectedMessage = String.format(EditDogCommand.MESSAGE_EDIT_DOG_SUCCESS, editedDog);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEntity(ID_THIRD_DOG, editedDog);

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDogCommand editEntityCommand = new EditDogCommand(ID_SECOND_DOG, new EditDogDescriptor());

        Dog editedDog = (Dog) model.getEntity(ID_SECOND_DOG);

        String expectedMessage = String.format(EditDogCommand.MESSAGE_EDIT_DOG_SUCCESS, editedDog);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDogUnfilteredList_failure() {
        Dog firstDog = (Dog) model.getEntity(ID_FIRST_DOG);
        EditDogDescriptor descriptor = new EditDogDescriptorBuilder(firstDog).build();
        EditDogCommand editEntityCommand = new EditDogCommand(ID_SECOND_DOG, descriptor);

        assertCommandFailure(editEntityCommand, model, Messages.MESSAGE_DUPLICATE_DOG);
    }

    @Test
    public void execute_invalidDogIdUnfilteredList_failure() {
        Integer outOfBoundId = model.getUnfilteredEntityList().stream()
                .map(Pair::getKey).sorted().collect(toList()).get(model.getUnfilteredEntityList().size() - 1) + 1;
        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withName(VALID_NAME_BELL).build();
        EditDogCommand editEntityCommand = new EditDogCommand(outOfBoundId, descriptor);

        assertCommandFailure(editEntityCommand, model, Messages.MESSAGE_INVALID_DOG_ID);
    }

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
