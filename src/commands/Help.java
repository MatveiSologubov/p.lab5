package src.commands;

import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CommandManager;

public class Help extends Command {
    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 0) throw new WrongAmountOfArgumentsException(0, args.length);

        System.out.println("Available commands:");
        commandManager.getAllCommands().forEach((name, cmd) ->
                System.out.printf("  %-27s%s%n", name, cmd.getHelp()));
    }

    @Override
    public String getHelp() {
        return "Shows this help message";
    }
}
