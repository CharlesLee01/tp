package dog.pawbook.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dog.pawbook.logic.commands.EditEntityCommand.EditEntityDescriptor;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * A utility class to help with building EditEntityDescriptor objects.
 */
public class EditEntityDescriptorBuilder<T> {

    protected EditEntityDescriptor descriptor;

    public EditEntityDescriptorBuilder() {
        descriptor = new EditEntityDescriptor();
    }

    public EditEntityDescriptorBuilder(EditEntityDescriptor descriptor) {
        this.descriptor = new EditEntityDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEntityDescriptor} with fields containing {@code entity}'s details
     */
    public EditEntityDescriptorBuilder(Entity entity) {
        descriptor = new EditEntityDescriptor();
        descriptor.setName(entity.getName());
        descriptor.setTags(entity.getTags());
    }

    /**
     * Returns an {@code EditEntityDescriptor} with fields containing {@code entity}'s details
     */
    public void setDescriptor(Entity entity) {
        descriptor.setName(entity.getName());
        descriptor.setTags(entity.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditEntityDescriptor} that we are building.
     */
    public T withName(String name) {
        descriptor.setName(new Name(name));
        return (T) this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEntityDescriptor}
     * that we are building.
     */
    public T withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return (T) this;
    }

    public EditEntityDescriptor build() {
        return descriptor;
    }
}
