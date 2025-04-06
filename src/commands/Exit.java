package src.commands;

import src.exceptions.WrongAmountOfArgumentsException;

public class Exit extends Command {
    private final Runnable exitHandler;

    public Exit(Runnable exitHandler) {
        this.exitHandler = exitHandler;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 0) throw new WrongAmountOfArgumentsException(0, args.length);

        System.out.println("Exiting program...");
        exitHandler.run();
    }

    @Override
    public String getHelp() {
        return "Exits the program";
    }
}
