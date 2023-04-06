package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCharacter.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntities.LEEROY;

class JsonAdaptedCharacterTest {
    private static final String INVALID_NAME = "John Cena!";
    private static final int INVALID_LEVEL = -1;
    private static final int INVALID_XP = -300000;
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = LEEROY.getName().toString();
    private static final JsonAdaptedStats VALID_STATS = new JsonAdaptedStats(LEEROY.getStats());
    private static final JsonAdaptedInventory VALID_INVENTORY = new JsonAdaptedInventory(LEEROY.getInventory());
    private static final List<JsonAdaptedTag> VALID_TAGS = LEEROY.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());
    private static final int VALID_LEVEL = LEEROY.getLevel();
    private static final int VALID_XP = LEEROY.getXP();

    @Test
    public void toModelType_validCharacterDetails_returnsCharacter() throws Exception {
        JsonAdaptedCharacter character = new JsonAdaptedCharacter(LEEROY);
        assertEquals(LEEROY, character.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCharacter character =
                new JsonAdaptedCharacter(INVALID_NAME, VALID_STATS, VALID_INVENTORY,
                        VALID_LEVEL, VALID_XP, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, character::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCharacter character =
                new JsonAdaptedCharacter(null, VALID_STATS, VALID_INVENTORY,
                        VALID_LEVEL, VALID_XP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, character::toModelType);
    }

    @Test
    public void toModelType_invalidXP_throwsIllegalValueException() {
        JsonAdaptedCharacter character =
                new JsonAdaptedCharacter(VALID_NAME, VALID_STATS, VALID_INVENTORY,
                        VALID_LEVEL, INVALID_XP, VALID_TAGS);
        String expectedMessage = JsonAdaptedCharacter.INVALID_XP;
        assertThrows(IllegalValueException.class, expectedMessage, character::toModelType);
    }

    @Test
    public void toModelType_invalidLevel_throwsIllegalValueException() {
        JsonAdaptedCharacter character =
                new JsonAdaptedCharacter(VALID_NAME, VALID_STATS, VALID_INVENTORY,
                        INVALID_LEVEL, VALID_XP, VALID_TAGS);
        String expectedMessage = JsonAdaptedCharacter.INVALID_LEVEL;
        assertThrows(IllegalValueException.class, expectedMessage, character::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedCharacter character =
                new JsonAdaptedCharacter(VALID_NAME, VALID_STATS, VALID_INVENTORY,
                        VALID_LEVEL, VALID_XP, invalidTags);
        assertThrows(IllegalValueException.class, character::toModelType);
    }
}
