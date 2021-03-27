package dog.pawbook.testutil;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building Owner objects.
 */
public class ProgramBuilder {

    public static final String DEFAULT_NAME = "Program1";

    private Name name;
    private Set<Session> sessions;
    private Set<Tag> tags;

    /**
     * Creates a {@code ProgramBuilder} with the default details.
     */
    public ProgramBuilder() {
        name = new Name(DEFAULT_NAME);
        sessions = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the ProgramBuilder with the data of {@code programToCopy}.
     */
    public ProgramBuilder(Program programToCopy) {
        name = programToCopy.getName();
        sessions = new HashSet<>(programToCopy.getSessions());
        tags = new HashSet<>(programToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Program} that we are building.
     */
    public ProgramBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Session>} and set it to the {@code Program} that we are building.
     */
    public ProgramBuilder withSessions(String ... sessions) {
        this.sessions = SampleDataUtil.getSessionSet(sessions);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Program} that we are building.
     */
    public ProgramBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Program build() {
        return new Program(name, sessions, tags);
    }

}
