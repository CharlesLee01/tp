package dog.pawbook.testutil;

import dog.pawbook.logic.commands.EditOwnerCommand.EditOwnerDescriptor;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.Phone;

/**
 * A utility class to help with building EditOwnerDescriptor objects.
 */
public class EditOwnerDescriptorBuilder extends EditEntityDescriptorBuilder<EditOwnerDescriptorBuilder> {

    public EditOwnerDescriptorBuilder() {
        descriptor = new EditOwnerDescriptor();
    }

    public EditOwnerDescriptorBuilder(EditOwnerDescriptor descriptor) {
        this.descriptor = new EditOwnerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditOwnerDescriptor} with fields containing {@code owner}'s details
     */
    public EditOwnerDescriptorBuilder(Owner owner) {
        descriptor = new EditOwnerDescriptor();
        setDescriptor(owner);
        EditOwnerDescriptor editOwnerDescriptor = (EditOwnerDescriptor) descriptor;
        editOwnerDescriptor.setPhone(owner.getPhone());
        editOwnerDescriptor.setEmail(owner.getEmail());
        editOwnerDescriptor.setAddress(owner.getAddress());
    }

    /**
     * Sets the {@code Phone} of the {@code EditOwnerDescriptor} that we are building.
     */
    public EditOwnerDescriptorBuilder withPhone(String phone) {
        EditOwnerDescriptor editOwnerDescriptor = (EditOwnerDescriptor) descriptor;
        editOwnerDescriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditOwnerDescriptor} that we are building.
     */
    public EditOwnerDescriptorBuilder withEmail(String email) {
        EditOwnerDescriptor editOwnerDescriptor = (EditOwnerDescriptor) descriptor;
        editOwnerDescriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditOwnerDescriptor} that we are building.
     */
    public EditOwnerDescriptorBuilder withAddress(String address) {
        EditOwnerDescriptor editOwnerDescriptor = (EditOwnerDescriptor) descriptor;
        editOwnerDescriptor.setAddress(new Address(address));
        return this;
    }

    public EditOwnerDescriptor build() {
        return (EditOwnerDescriptor) descriptor;
    }
}
