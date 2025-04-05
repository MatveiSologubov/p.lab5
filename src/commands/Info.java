package src.commands;

import src.managers.CollectionManager;

public class Info extends Command {
    final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * executes command
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            System.out.println("Wrong number of arguments");
            return;
        }

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
