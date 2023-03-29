package seedu.address.experimental.model;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;

/**
 * Abstraction of all characters
 */
public class RerollCharacters implements ReadOnlyEntities {

    private final ObservableList<Entity> characters;

    /**
     * Initialize with list of characters.
     * @param characters Unmodifiable view of characters.
     */
    public RerollCharacters(ObservableList<Entity> characters) {
        this.characters = characters;
    }

    @Override
    public ObservableList<Entity> getEntityList() {
        return characters;
    }

    @Override
    public Entity getEntityWithName(String name) {
        Entity toReturn = null;
        for (Entity character : characters) {
            if (character.getName().fullName.equals(name)) {
                toReturn = character;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RerollCharacters
                && characters.equals(((RerollCharacters) other).characters));
    }

}
