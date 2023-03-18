package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntities.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Name;

/**
 * Contains integration tests (interaction with the Model) for {@code AddEntityCommand}.
 */
public class AddEntityCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newEntity_success() {
        Entity validEntity = new Entity(new Name("test guy"));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addEntity(validEntity);

        assertCommandSuccess(new AddEntityCommand(validEntity), model,
                String.format(AddEntityCommand.MESSAGE_SUCCESS, validEntity), expectedModel);
    }

    @Test
    public void execute_duplicateEntity_throwsCommandException() {
        Entity personInList = model.getAddressBook().getEntityList().get(0);
        assertCommandFailure(new AddEntityCommand(personInList), model, AddEntityCommand.MESSAGE_DUPLICATE_ENTITY);
    }

}
