package mycelium.mycelium.logic.commands;

import static java.util.Objects.requireNonNull;

import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.CliSyntax;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.person.Person;
import mycelium.mycelium.model.project.Project;

/**
 * Adds a new project to Mycelium.
 */
public class AddProjectCommand extends Command {
    public static final String COMMAND_ACRONYM = "p";

    public static final String MESSAGE_USAGE = COMMAND_ACRONYM + ": Adds a project to Mycelium. "
            + "Parameters: "
            + CliSyntax.PREFIX_PROJECT_NAME + "PROJECT NAME "
            + CliSyntax.PREFIX_CLIENT_NAME + "CLIENT NAME "
            + CliSyntax.PREFIX_PROJECT_STATUS + "PROJECT STATUS "
            + CliSyntax.PREFIX_SOURCE + "PROJECT SOURCE "
            + CliSyntax.PREFIX_PROJECT_DESCRIPTION + "PROJECT DESCRIPTION "
            + CliSyntax.PREFIX_ACCEPTED_DATE + "DATE PROJECT WAS ACCEPTED "
            + CliSyntax.PREFIX_DEADLINE_DATE + "DEADLINE OF PROJECT "
            + "Example: " + COMMAND_ACRONYM + " "
            + CliSyntax.PREFIX_PROJECT_NAME + "Mycelium "
            + CliSyntax.PREFIX_CLIENT_NAME + "Alice Baker "
            + CliSyntax.PREFIX_PROJECT_STATUS + "done "
            + CliSyntax.PREFIX_SOURCE + "Fiverr "
            + CliSyntax.PREFIX_PROJECT_DESCRIPTION + "Cli-based project management app "
            + CliSyntax.PREFIX_ACCEPTED_DATE + "1-Dec-2023"
            + CliSyntax.PREFIX_DEADLINE_DATE + "31-Dec-2023";

    public static final String MESSAGE_SUCCESS = "New project added: %1$s";

    public static final String MESSAGE_DUPLICATE_PROJECT_NAME = "This project name already exists in your project list";

    private final Project toAdd;

    public AddProjectCommand(Project project) {
        requireNonNull(project);
        toAdd = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT_NAME);
        }

        model.addProject(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }



}
