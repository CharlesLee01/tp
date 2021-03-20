package dog.pawbook.model;

import static dog.pawbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalOwners.ALICE;
import static dog.pawbook.testutil.TypicalOwners.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.exceptions.DuplicateOwnerException;
import dog.pawbook.testutil.OwnerBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getEntityList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateOwners_throwsDuplicateOwnerException() {
        // Two owners with the same identity fields
        Owner editedAlice = new OwnerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Entity> newOwners = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newOwners);

        assertThrows(DuplicateOwnerException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasOwner_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasEntity(null));
    }

    @Test
    public void hasOwner_ownerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasEntity(ALICE));
    }

    @Test
    public void hasOwner_ownerInAddressBook_returnsTrue() {
        addressBook.addEntity(ALICE);
        assertTrue(addressBook.hasEntity(ALICE));
    }

    @Test
    public void hasOwner_ownerWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addEntity(ALICE);
        Owner editedAlice = new OwnerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasEntity(editedAlice));
    }

    @Test
    public void getOwnerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getEntityList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose owners list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Entity> owners = FXCollections.observableArrayList();

        AddressBookStub(Collection<Entity> owners) {
            this.owners.setAll(owners);
        }

        @Override
        public ObservableList<Entity> getEntityList() {
            return owners;
        }
    }

}
