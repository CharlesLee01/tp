package dog.pawbook.testutil;

import dog.pawbook.logic.commands.EditDogCommand.EditDogDescriptor;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.dog.Sex;

/**
 * A utility class to help with building EditDogDescriptor objects.
 */
public class EditDogDescriptorBuilder extends EditEntityDescriptorBuilder<EditDogDescriptorBuilder> {

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
        setDescriptor(dog);
        EditDogDescriptor editProgramDescriptor = (EditDogDescriptor) descriptor;
        editProgramDescriptor.setBreed(dog.getBreed());
        editProgramDescriptor.setDob(dog.getDob());
        editProgramDescriptor.setSex(dog.getSex());
        editProgramDescriptor.setOwnerId(dog.getOwnerId());
    }

    /**
     * Sets the {@code Breed} of the {@code EditDogDescriptor} that we are building.
     */
    public EditDogDescriptorBuilder withBreed(String breed) {
        EditDogDescriptor editProgramDescriptor = (EditDogDescriptor) descriptor;
        editProgramDescriptor.setBreed(new Breed(breed));
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code EditDogDescriptor} that we are building.
     */
    public EditDogDescriptorBuilder withDob(String dob) {
        EditDogDescriptor editProgramDescriptor = (EditDogDescriptor) descriptor;
        editProgramDescriptor.setDob(new DateOfBirth(dob));
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code EditDogDescriptor} that we are building.
     */
    public EditDogDescriptorBuilder withSex(String sex) {
        EditDogDescriptor editProgramDescriptor = (EditDogDescriptor) descriptor;
        editProgramDescriptor.setSex(new Sex(sex));
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code EditDogDescriptor} that we are building.
     */
    public EditDogDescriptorBuilder withOwnerId(int ownerId) {
        EditDogDescriptor editProgramDescriptor = (EditDogDescriptor) descriptor;
        editProgramDescriptor.setOwnerId(ownerId);
        return this;
    }

    public EditDogDescriptor build() {
        return (EditDogDescriptor) descriptor;
    }
}
