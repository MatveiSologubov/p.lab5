package src.commands;

import src.managers.CollectionManager;
import src.managers.FileManager;

public class Save extends Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;
    private final String filePath;

    public Save(CollectionManager collectionManager, FileManager fileManager, String filePath) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        this.filePath = filePath;
    }

    /**
     * execute command
     */
    @Override
    public void execute() {
        fileManager.save(collectionManager.getCollection(), filePath);
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Saves collection to file";
    }
}
