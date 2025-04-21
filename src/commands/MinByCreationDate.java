package src.commands;

import src.exceptions.CollectionIsEmptyException;
import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.models.Ticket;

import java.util.Set;

/**
 * 'Min By Creation Date' print Ticket with minimum creation date
 */
public class MinByCreationDate extends Command {
    private final CollectionManager collectionManager;

    public MinByCreationDate(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * execute command
     * @param args arguments for command
     * @throws WrongAmountOfArgumentsException if user provides wrong amount of arguments
     */
    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException(0, args.length);
            Set<Ticket> collection = collectionManager.getCollection();
            if (collection.isEmpty()) {
                throw new CollectionIsEmptyException();
            }

            Ticket minTicket = collection.iterator().next();
            for (Ticket t : collection) {
                if (t.compareTo(minTicket) < 0) {
                    minTicket = t;
                }
            }

            System.out.println(minTicket);
        } catch (CollectionIsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Shows element with minimum creation date";
    }
}
