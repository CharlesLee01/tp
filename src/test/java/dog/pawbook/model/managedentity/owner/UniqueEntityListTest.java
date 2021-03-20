package dog.pawbook.model.managedentity.owner;

import static dog.pawbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalOwners.ALICE;
import static dog.pawbook.testutil.TypicalOwners.BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.owner.exceptions.DuplicateEntityException;
import dog.pawbook.model.managedentity.owner.exceptions.EntityNotFoundException;
import dog.pawbook.testutil.OwnerBuilder;
import javafx.util.Pair;

public class UniqueEntityListTest {

    private final UniqueEntityList uniqueEntityList = new UniqueEntityList();

    @Test
    public void contains_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.contains(null));
    }

    @Test
    public void contains_ownerNotInList_returnsFalse() {
        assertFalse(uniqueEntityList.contains(ALICE));
    }

    @Test
    public void contains_ownerInList_returnsTrue() {
        uniqueEntityList.add(ALICE);
        assertTrue(uniqueEntityList.contains(ALICE));
    }

    @Test
    public void contains_ownerWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEntityList.add(ALICE);
        Owner editedAlice = new OwnerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueEntityList.contains(editedAlice));
    }

    @Test
    public void add_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.add(null));
    }

    @Test
    public void add_duplicateOwner_throwsDuplicateOwnerException() {
        uniqueEntityList.add(ALICE);
        assertThrows(DuplicateEntityException.class, () -> uniqueEntityList.add(ALICE));
    }

    @Test
    public void setOwner_nullEditedOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.setEntity(1, null));
    }

    @Test
    public void setOwner_targetOwnerNotInList_throwsOwnerNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> uniqueEntityList.setEntity(1, ALICE));
    }

    @Test
    public void remove_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.remove(null));
    }

    @Test
    public void remove_ownerDoesNotExist_throwsOwnerNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> uniqueEntityList.remove(ALICE));
    }

    @Test
    public void remove_existingOwner_removesOwner() {
        uniqueEntityList.add(ALICE);
        uniqueEntityList.remove(ALICE);
        UniqueEntityList expectedUniqueOwnerList = new UniqueEntityList();
        assertEquals(expectedUniqueOwnerList, uniqueEntityList);
    }

    @Test
    public void setOwners_nullUniqueOwnerList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.setEntities((UniqueEntityList) null));
    }

    @Test
    public void setOwners_uniqueOwnerList_replacesOwnListWithProvidedUniqueOwnerList() {
        uniqueEntityList.add(ALICE);
        UniqueEntityList expectedUniqueOwnerList = new UniqueEntityList();
        expectedUniqueOwnerList.add(BOB);
        uniqueEntityList.setEntities(expectedUniqueOwnerList);
        assertEquals(expectedUniqueOwnerList, uniqueEntityList);
    }

    @Test
    public void setOwners_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList
                .setEntities((List<Pair<Integer, Entity>>) null));
    }

    @Test
    public void setOwners_list_replacesOwnListWithProvidedList() {
        uniqueEntityList.add(ALICE);
        List<Pair<Integer, Entity>> entityList = Collections.singletonList(new Pair<>(1, BOB));
        uniqueEntityList.setEntities(entityList);
        UniqueEntityList expectedUniqueOwnerList = new UniqueEntityList();
        expectedUniqueOwnerList.add(BOB);
        assertEquals(expectedUniqueOwnerList, uniqueEntityList);
    }

    @Test
    public void setOwners_listWithDuplicateOwners_throwsDuplicateOwnerException() {
        List<Pair<Integer, Entity>> listWithDuplicateEntities =
                Arrays.asList(new Pair<>(1, ALICE), new Pair<>(1, ALICE));
        assertThrows(DuplicateEntityException.class, () -> uniqueEntityList.setEntities(listWithDuplicateEntities));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueEntityList
                .asUnmodifiableObservableList()
                .remove(0));
    }
}
