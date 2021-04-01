package dog.pawbook.testutil;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building Owner objects.
 */
public class ProgramBuilder extends EntityBuilder<ProgramBuilder> {

    private Set<Session> sessions;

    /**
     * Creates a {@code ProgramBuilder} with the default details.
     */
    public ProgramBuilder() {
        super();
        sessions = new HashSet<>();
    }

    /**
     * Initializes the ProgramBuilder with the data of {@code programToCopy}.
     */
    public ProgramBuilder(Program programToCopy) {
        super(programToCopy);
        sessions = new HashSet<>(programToCopy.getSessions());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Session>} and set it to the {@code Program} that we are building.
     */
    public ProgramBuilder withSessions(String ... sessions) {
        this.sessions = SampleDataUtil.getSessionSet(sessions);
        return this;
    }

    public Program build() {
        return new Program(name, sessions, tags);
    }
}
