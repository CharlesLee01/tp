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
abstract class EntityBuilder<T extends EntityBuilder<T, ? extends Entity>, S extends Entity> {

    public static final String DEFAULT_NAME = "Entity Name Not Specified";

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
    public EntityBuilder(S entityToCopy) {
        name = entityToCopy.getName();
        tags = new HashSet<>(entityToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Entity} that we are building.
     */
    public final T withName(String name) {
        this.name = new Name(name);
        return self();
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Entity} that we are building.
     */
    public final T withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return self();
    }

    public abstract S build();

    protected abstract T self();

}
