package src.commands;

import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.models.Ticket;

import java.util.Set;

/**
 * 'Show' command prints all Tickets in collection
 */
public class Show extends Command {
    private final CollectionManager collectionManager;

    public Show(CollectionManager manger) {
        this.collectionManager = manger;
    }

    /**
     * execute command
     * @param args arguments for command
     * @throws WrongAmountOfArgumentsException if user provides wrong amount of arguments
     */
    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        if (args.length != 0) throw new WrongAmountOfArgumentsException(0, args.length);

        Set<Ticket> tickets = collectionManager.getCollection();
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "This command will show current collection";
    }
}
