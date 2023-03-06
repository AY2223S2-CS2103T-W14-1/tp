package mycelium.mycelium.logic.commands;

import static java.util.Objects.requireNonNull;

import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.CliSyntax;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
        + "Parameters: "
        + CliSyntax.PREFIX_NAME + "NAME "
        + CliSyntax.PREFIX_PHONE + "PHONE "
        + CliSyntax.PREFIX_EMAIL + "EMAIL "
        + CliSyntax.PREFIX_ADDRESS + "ADDRESS "
        + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_NAME + "John Doe "
        + CliSyntax.PREFIX_PHONE + "98765432 "
        + CliSyntax.PREFIX_EMAIL + "johnd@example.com "
        + CliSyntax.PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
        + CliSyntax.PREFIX_TAG + "friends "
        + CliSyntax.PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
