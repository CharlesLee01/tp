package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_PROGRAM_1;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_PROGRAM_2;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_PROGRAM2;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_PROGRAM2;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_DOGS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.testutil.EditProgramDescriptorBuilder;

public class EditProgramDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditProgramDescriptor descriptorWithSameValues = new EditProgramDescriptor(DESC_PROGRAM_1);
        assertTrue(DESC_PROGRAM_1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_PROGRAM_1.equals(DESC_PROGRAM_1));

        // null -> returns false
        assertFalse(DESC_PROGRAM_1.equals(null));

        // different types -> returns false
        assertFalse(DESC_PROGRAM_1.equals(5));

        // different values -> returns false
        assertFalse(DESC_PROGRAM_1.equals(DESC_PROGRAM_2));

        // different name -> returns false
        EditProgramDescriptor editedProgram1 = new EditProgramDescriptorBuilder(DESC_PROGRAM_1)
                .withName(VALID_NAME_PROGRAM2).build();
        assertFalse(DESC_PROGRAM_1.equals(editedProgram1));

        // different session -> returns false
        editedProgram1 = new EditProgramDescriptorBuilder(DESC_PROGRAM_1).withSessions(VALID_SESSION_PROGRAM2).build();
        assertFalse(DESC_PROGRAM_1.equals(editedProgram1));

        // different tags -> returns false
        editedProgram1 = new EditProgramDescriptorBuilder(DESC_PROGRAM_1).withTags(VALID_TAG_DOGS).build();
        assertFalse(DESC_PROGRAM_1.equals(editedProgram1));
    }
}
