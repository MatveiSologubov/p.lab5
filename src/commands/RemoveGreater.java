package src.commands;

import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.managers.ScannerManager;
import src.models.Ticket;
import src.models.builders.TicketBuilder;

import java.util.Set;

public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public RemoveGreater(CollectionManager collectionManager, ScannerManager scannerManager) {
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    /**
     * execute command
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 0) throw new WrongAmountOfArgumentsException(0, args.length);
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }

        Set<Ticket> collection = collectionManager.getCollection();
        Ticket target = new TicketBuilder(scannerManager.getScanner()).build();
        collection.removeIf(ticket -> ticket.compareTo(target) > 0);
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Removes all tickets bigger than specified element";
    }
}
