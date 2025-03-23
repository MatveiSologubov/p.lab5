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
    public void execute(String[] args) {
        if (args.length != 0) {
            System.out.println("Wrong number of arguments");
            return;
        }

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
