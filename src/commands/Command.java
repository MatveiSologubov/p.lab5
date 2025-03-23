package src.commands;

public abstract class Command {
    /**
     * execute command
     */
    public abstract void execute(String[] args);

    /**
     * @return Help message
     */
    public abstract String getHelp();
}
