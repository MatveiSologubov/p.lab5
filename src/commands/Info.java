package src.commands;

import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;

/**
 * 'Info' command print information about current collection
 */
public class Info extends Command {
    final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * execute command
     * @param args arguments for command
     * @throws WrongAmountOfArgumentsException if used provides wrong amount of arguments
     */
    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        if (args.length != 0) throw new WrongAmountOfArgumentsException(0, args.length);

        System.out.println("Collection Info:");
        System.out.println(" Type: " + collectionManager.getCollectionType());
        System.out.println(" Collection size: " + collectionManager.getCollectionSize());
        System.out.println(" Initialization time: " + collectionManager.getInitTime());
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "This command print information about current collection.";
    }
}
