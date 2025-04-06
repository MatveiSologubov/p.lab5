package src.commands;

import src.exceptions.WrongAmountOfArgumentsException;

public abstract class Command {
    /**
     * execute command
     */
    public abstract void execute(String[] args) throws WrongAmountOfArgumentsException;

    /**
     * @return Help message
     */
    public abstract String getHelp();
}
