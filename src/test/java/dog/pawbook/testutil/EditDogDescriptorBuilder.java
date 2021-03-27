package dog.pawbook.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dog.pawbook.logic.commands.EditDogCommand.EditDogDescriptor;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.dog.Sex;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * A utility class to help with building EditDogDescriptor objects.
 */
public class EditDogDescriptorBuilder {

    private EditDogDescriptor descriptor;

    public EditDogDescriptorBuilder() {
        descriptor = new EditDogDescriptor();
    }

    public EditDogDescriptorBuilder(EditDogDescriptor descriptor) {
        this.descriptor = new EditDogDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDogDescriptor} with fields containing {@code dog}'s details
     */
    public EditDogDescriptorBuilder(Dog dog) {
        descriptor = new EditDogDescriptor();
        descriptor.setName(dog.getName());
        descriptor.setBreed(dog.getBreed());
        descriptor.setDob(dog.getDob());
        descriptor.setSex(dog.getSex());
        descriptor.setOwnerId(dog.getOwnerId());
        descriptor.setTags(dog.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditDogDescriptor} that we are building.
     */
    public EditDogDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Breed} of the {@code EditDogDescriptor} that we are building.
     */
    public EditDogDescriptorBuilder withBreed(String breed) {
        descriptor.setBreed(new Breed(breed));
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code EditDogDescriptor} that we are building.
     */
    public EditDogDescriptorBuilder withDob(String dob) {
        descriptor.setDob(new DateOfBirth(dob));
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code EditDogDescriptor} that we are building.
     */
    public EditDogDescriptorBuilder withSex(String sex) {
        descriptor.setSex(new Sex(sex));
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code EditDogDescriptor} that we are building.
     */
    public EditDogDescriptorBuilder withOwnerId(int ownerId) {
        descriptor.setOwnerId(ownerId);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditDogDescriptor}
     * that we are building.
     */
    public EditDogDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditDogDescriptor build() {
        return descriptor;
    }
}
