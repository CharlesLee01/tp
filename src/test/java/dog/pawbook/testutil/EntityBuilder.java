package dog.pawbook.testutil;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building Owner objects.
 */
public class EntityBuilder<T> {

    public static final String DEFAULT_NAME = "EntityName";

    protected Name name;
    protected Set<Tag> tags;

    /**
     * Creates a {@code EntityBuilder} with the default details.
     */
    public EntityBuilder() {
        name = new Name(DEFAULT_NAME);
        tags = new HashSet<>();
    }

    /**
     * Initializes the EntityBuilder with the data of {@code entityToCopy}.
     */
    public EntityBuilder(Entity entityToCopy) {
        name = entityToCopy.getName();
        tags = new HashSet<>(entityToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Entity} that we are building.
     */
    public T withName(String name) {
        this.name = new Name(name);
        return (T) this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Entity} that we are building.
     */
    public T withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return (T) this;
    }
}
