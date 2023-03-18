package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.entity.Entity;

/**
 * A utility class containing a list of {@code Entity} objects to be used in tests.
 */
public class TypicalEntities {

    public static final Entity ALICE = new EntityBuilder().withName("Alice Pauline")
            .withTags("friends").build();
    public static final Entity BENSON = new EntityBuilder().withName("Benson Meier")
            .withTags("owesMoney", "friends").build();
    public static final Entity CARL = new EntityBuilder().withName("Carl Kurz").build();
    public static final Entity DANIEL = new EntityBuilder().withName("Daniel Meier").withTags("friends").build();
    public static final Entity ELLE = new EntityBuilder().withName("Elle Meyer").build();
    public static final Entity FIONA = new EntityBuilder().withName("Fiona Kunz").build();
    public static final Entity GEORGE = new EntityBuilder().withName("George Best").build();

    // Manually added
    public static final Entity HOON = new EntityBuilder().withName("Hoon Meier").build();
    public static final Entity IDA = new EntityBuilder().withName("Ida Mueller").build();

    // Manually added - Entity's details found in {@code CommandTestUtil}
    public static final Entity AMY = new EntityBuilder().withName(VALID_NAME_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Entity BOB = new EntityBuilder().withName(VALID_NAME_BOB)
                                                        .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                                                        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEntities() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical entities.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Entity entity : getTypicalEntities()) {
            ab.addEntity(entity);
        }
        return ab;
    }

    public static List<Entity> getTypicalEntities() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
