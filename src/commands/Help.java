package src.commands;

import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CommandManager;

/**
 * 'Help' command provides description and usage of all commands that are available
 */
public class Help extends Command {
    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * execute command
     * @param args arguments for command
     * @throws WrongAmountOfArgumentsException if user provides wrong amount of arguments
     */
    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        if (args.length != 0) throw new WrongAmountOfArgumentsException(0, args.length);

        System.out.println("Available commands:");
        commandManager.getAllCommands().forEach((name, cmd) ->
                System.out.printf("  %-27s%s%n", name, cmd.getHelp()));
    }

    /**
     *
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Shows this help message";
    }
}
