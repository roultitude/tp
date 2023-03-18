package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.entity.Name;
import seedu.address.model.entity.Entity;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Entity objects.
 */
public class EntityBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;

    private Set<Tag> tags;

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
    public EntityBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Entity} that we are building.
     */
    public EntityBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }



    public Entity build() {
        return new Entity(name, tags);
    }

}
