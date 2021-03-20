package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DateOfBirthTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDob(null));

        // invalid addresses
        assertFalse(DateOfBirth.isValidDob("")); // empty string
        assertFalse(DateOfBirth.isValidDob(" ")); // spaces only
        assertFalse(DateOfBirth.isValidDob("00-00-0000"));
        assertFalse(DateOfBirth.isValidDob("00-00-0001"));
        assertFalse(DateOfBirth.isValidDob("00-01-0000"));
        assertFalse(DateOfBirth.isValidDob("01-00-0000"));
        assertFalse(DateOfBirth.isValidDob("29-02-2021"));
        assertFalse(DateOfBirth.isValidDob("31-11-2021"));
        assertFalse(DateOfBirth.isValidDob("32-1-21"));

        // valid addresses
        assertTrue(DateOfBirth.isValidDob("29-02-2020"));
        assertTrue(DateOfBirth.isValidDob("1-1-21"));
        assertTrue(DateOfBirth.isValidDob("1-01-21"));
        assertTrue(DateOfBirth.isValidDob("01-1-21"));
        assertTrue(DateOfBirth.isValidDob("01-01-21"));
    }
}
