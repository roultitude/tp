package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ENTITIES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.experimental.model.ReadOnlyEntities;
import seedu.address.experimental.model.RerollEntities;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.UiSwitchMode;
import seedu.address.model.Model;
import seedu.address.model.entity.Entity;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_SUCCESS = "Entered Edit Mode";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the person identified by the index number used in the displayed person list.\n"
        + "Parameters: CLASSIFICATION (char, mob or item) NAME (name of entity)\n"
        + "Example: " + COMMAND_WORD + " item short dagger";

    private final String toEditName;
    private final String toEditClassification;

    public EditCommand(String toEditClassification, String toEditName) {
        this.toEditClassification = toEditClassification;
        this.toEditName = toEditName;
    }

    @Override
    public CommandResult execute(seedu.address.experimental.model.Model model) throws CommandException {
        requireNonNull(model);
        List<Entity> lastShownList = model.getFilteredEntityList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Entity entityToEdit = lastShownList.get(index.getZeroBased());
        Entity editedEntity = createEditedPerson(entityToEdit, editPersonDescriptor);

        if (!entityToEdit.isSamePerson(editedEntity) && model.hasEntity(editedEntity)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setEntity(entityToEdit, editedEntity);
        model.updateFilteredEntityList(PREDICATE_SHOW_ALL_ENTITIES);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedEntity));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit} edited with {@code
     * editPersonDescriptor}.
     */
    private static Entity createEditedPerson(Entity entityToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert entityToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(entityToEdit.getName());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(entityToEdit.getTags());

        return new Entity(updatedName, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
            && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the corresponding field value
     * of the person.
     */
    public static class EditPersonDescriptor {

        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException} if modification is
         * attempted. Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }
        }

        throw new CommandException(String.format("No such entity in %s!", toEditClassification));
    }
}

