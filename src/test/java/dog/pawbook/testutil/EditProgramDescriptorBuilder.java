package dog.pawbook.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * A utility class to help with building EditProgramDescriptor objects.
 */
public class EditProgramDescriptorBuilder {

    private EditProgramDescriptor descriptor;

    public EditProgramDescriptorBuilder() {
        descriptor = new EditProgramDescriptor();
    }

    public EditProgramDescriptorBuilder(EditProgramDescriptor descriptor) {
        this.descriptor = new EditProgramDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProgramDescriptor} with fields containing {@code program}'s details
     */
    public EditProgramDescriptorBuilder(Program program) {
        descriptor = new EditProgramDescriptor();
        descriptor.setName(program.getName());
        descriptor.setSessions(program.getSessions());
        descriptor.setTags(program.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditProgramDescriptor} that we are building.
     */
    public EditProgramDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Parses the {@code sessions} into a {@code Set<Session>} and set it to the {@code EditProgramDescriptor}
     * that we are building.
     */
    public EditProgramDescriptorBuilder withSessions(String... sessions) {
        Set<Session> sessionSet = Stream.of(sessions).map(Session::new).collect(Collectors.toSet());
        descriptor.setSessions(sessionSet);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditProgramDescriptor}
     * that we are building.
     */
    public EditProgramDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditProgramDescriptor build() {
        return descriptor;
    }
}
