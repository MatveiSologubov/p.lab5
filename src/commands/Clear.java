package src.commands;

import src.managers.CollectionManager;

public class Clear extends Command {
   private final CollectionManager collectionManager;

   public Clear(CollectionManager collectionManager) {
       this.collectionManager = collectionManager;
   }

    /**
     * execute command
     */
    @Override
    public void execute() {
        collectionManager.clearCollection();
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Clears the collection";
    }
}
