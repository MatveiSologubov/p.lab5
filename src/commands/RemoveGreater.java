package src.commands;

import src.exceptions.CollectionIsEmptyException;
import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.managers.ScannerManager;
import src.models.Ticket;
import src.models.builders.TicketBuilder;

import java.util.Set;

/**
 * 'Remove Greater' command builds ticket and then removes all tickets with bigger price than built ticket
 */
public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public RemoveGreater(CollectionManager collectionManager, ScannerManager scannerManager) {
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
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
            if (collectionManager.getCollection().isEmpty()) {
                throw new CollectionIsEmptyException();
            }

            Set<Ticket> collection = collectionManager.getCollection();
            Ticket target = new TicketBuilder(scannerManager.getScanner()).build();
            collection.removeIf(ticket -> ticket.compareTo(target) > 0);

        } catch (CollectionIsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Removes all tickets bigger than specified element";
    }
}
