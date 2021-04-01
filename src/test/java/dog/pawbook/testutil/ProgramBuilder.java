package dog.pawbook.testutil;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building Owner objects.
 */
public class ProgramBuilder extends EntityBuilder<ProgramBuilder, Program> {

    private Set<Session> sessions;
    private Set<Integer> enrolledDogIds;

    /**
     * Creates a {@code ProgramBuilder} with the default details.
     */
    public ProgramBuilder() {
        super();
        sessions = new HashSet<>();
        enrolledDogIds = new HashSet<>();
    }

    /**
     * Initializes the ProgramBuilder with the data of {@code programToCopy}.
     */
    public ProgramBuilder(Program programToCopy) {
        super(programToCopy);
        sessions = new HashSet<>(programToCopy.getSessions());
    }

    @Override
    protected ProgramBuilder self() {
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Session>} and set it to the {@code Program} that we are building.
     */
    public ProgramBuilder withSessions(String ... sessions) {
        this.sessions = SampleDataUtil.getSessionSet(sessions);
        return self();
    }

    /**
     * Parses the {@code dogIds} into a {@code Set<Integer>} and set it to the {@code Program} that we are building.
     */
    public final ProgramBuilder withDogs(Integer... dogIds) {
        this.enrolledDogIds = SampleDataUtil.getIdSet(dogIds);
        return self();
    }

    public Program build() {
        return new Program(name, sessions, tags);
    }
}
