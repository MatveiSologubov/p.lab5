package src.commands;

public class Exit extends Command{
    private final Runnable exitHandler;

    public Exit(Runnable exitHandler) {
        this.exitHandler = exitHandler;
    }

    @Override
    public void execute() {
        System.out.println("Exiting program...");
        exitHandler.run();
    }

    @Override
    public String getHelp() {
        return "Exits the program";
    }
}
