package dog.pawbook.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;

/**
 * A utility class to help with building EditProgramDescriptor objects.
 */
public class EditProgramDescriptorBuilder extends EditEntityDescriptorBuilder<EditProgramDescriptorBuilder> {

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
        setDescriptor(program);
        EditProgramDescriptor editProgramDescriptor = (EditProgramDescriptor) descriptor;
        editProgramDescriptor.setSessions(program.getSessions());
    }

    /**
     * Parses the {@code sessions} into a {@code Set<Session>} and set it to the {@code EditProgramDescriptor}
     * that we are building.
     */
    public EditProgramDescriptorBuilder withSessions(String... sessions) {
        EditProgramDescriptor editProgramDescriptor = (EditProgramDescriptor) descriptor;
        Set<Session> sessionSet = Stream.of(sessions).map(Session::new).collect(Collectors.toSet());
        editProgramDescriptor.setSessions(sessionSet);
        return this;
    }

    public EditProgramDescriptor build() {
        return (EditProgramDescriptor) descriptor;
    }
}
