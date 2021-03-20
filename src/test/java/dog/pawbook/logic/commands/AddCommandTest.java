package dog.pawbook.logic.commands;

import static dog.pawbook.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.AddressBook;
import dog.pawbook.model.Model;
import dog.pawbook.model.ReadOnlyAddressBook;
import dog.pawbook.model.ReadOnlyUserPrefs;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.testutil.OwnerBuilder;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class AddCommandTest {

    @Test
    public void constructor_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOwnerCommand(null));
    }

    @Test
    public void execute_ownerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEntityAdded modelStub = new ModelStubAcceptingEntityAdded();
        Owner validOwner = new OwnerBuilder().build();

        CommandResult commandResult = new AddOwnerCommand(validOwner).execute(modelStub);

        assertEquals(AddOwnerCommand.MESSAGE_SUCCESS + validOwner, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOwner), modelStub.entitiesAdded);
    }

    @Test
    public void execute_duplicateOwner_throwsCommandException() {
        Owner validOwner = new OwnerBuilder().build();
        AddOwnerCommand addOwnerCommand = new AddOwnerCommand(validOwner);
        ModelStub modelStub = new ModelStubWithOwner(validOwner);

        assertThrows(CommandException.class,
                AddOwnerCommand.MESSAGE_DUPLICATE_OWNER, () -> addOwnerCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Owner alice = new OwnerBuilder().withName("Alice").build();
        Owner bob = new OwnerBuilder().withName("Bob").build();
        AddOwnerCommand addAliceCommand = new AddOwnerCommand(alice);
        AddOwnerCommand addBobCommand = new AddOwnerCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddOwnerCommand addAliceCommandCopy = new AddOwnerCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different owner -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEntity(Entity entity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEntity(Entity entity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEntity(int id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEntity(int targetId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEntity(int targetId, Entity editedEntity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Pair<Integer, Entity>> getFilteredEntityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEntityList(Predicate<Pair<Integer, Entity>> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single owner.
     */
    private class ModelStubWithOwner extends ModelStub {
        private final Owner owner;

        ModelStubWithOwner(Owner owner) {
            requireNonNull(owner);
            this.owner = owner;
        }

        @Override
        public boolean hasEntity(Entity entity) {
            requireNonNull(entity);
            return this.owner.isSameEntity(entity);
        }
    }

    /**
     * A Model stub that always accept the owner being added.
     */
    private class ModelStubAcceptingEntityAdded extends ModelStub {
        final ArrayList<Entity> entitiesAdded = new ArrayList<>();

        @Override
        public boolean hasEntity(Entity entity) {
            requireNonNull(entity);
            return entitiesAdded.stream().anyMatch(entity::isSameEntity);
        }

        @Override
        public void addEntity(Entity entity) {
            requireNonNull(entity);
            entitiesAdded.add(entity);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
