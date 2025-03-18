package src.commands;

import src.managers.CommandManager;

public class Help extends Command {
    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute() {
        System.out.println("Available src.commands:");
        commandManager.getAllCommands().forEach((name, cmd) ->
                System.out.printf("  %-10s%s%n", name, cmd.getHelp()));
    }

    @Override
    public String getHelp() {
        return "Shows this help message";
    }
}
