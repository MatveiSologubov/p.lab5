package src.commands;

import src.managers.CollectionManager;
import src.managers.CommandManager;

public class Info extends Command{
    CollectionManager collectionManager;
    CommandManager commandManager;

    public Info(CollectionManager collectionManager, CommandManager commandManager){
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    /**
     * executes command
     */
    @Override
    public void execute() {
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
