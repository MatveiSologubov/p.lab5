package src.commands;

import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.managers.FileManager;

/**
 * 'Save' command saves collection to file
 */
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
     * @param args arguments for command
     * @throws WrongAmountOfArgumentsException if user provides wrong amount of arguments
     */
    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        if (args.length != 0) throw new WrongAmountOfArgumentsException(0, args.length);

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
