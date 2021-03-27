package dog.pawbook.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dog.pawbook.model.AddressBook;
import dog.pawbook.model.managedentity.program.Program;

/**
 * A utility class containing a list of {@code Program} objects to be used in tests.
 */
public class TypicalPrograms {

    public static final Program program1 = new ProgramBuilder().withName("Obedience Training")
            .withSessions("01-02-2021 18:00").withTags("puppies").build();
    public static final Program program2 = new ProgramBuilder().withName("Endurance Training")
            .withSessions("12-12-2012 16:00").withTags("dogs").build();
    public static final Program program3 = new ProgramBuilder().withName("Strength Training")
            .withSessions("01-01-2011 11:11").withTags("poodles").build();

    private TypicalPrograms() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical programs.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Program program : getTypicalPrograms()) {
            ab.addEntity(program);
        }
        return ab;
    }

    public static List<Program> getTypicalPrograms() {
        return new ArrayList<>(Arrays.asList(program1, program2, program3));
    }
}
