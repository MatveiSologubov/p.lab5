package src.commands;

import src.exceptions.DuplicateIdException;
import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.managers.ScannerManager;
import src.models.Ticket;
import src.models.builders.TicketBuilder;

import java.util.Collections;

public class AddIfMin extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public AddIfMin(CollectionManager collectionManager, ScannerManager scannerManager) {
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    /**
     * execute command
     *
     * @param args
     */
    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        if (args.length != 0) throw new WrongAmountOfArgumentsException(0, args.length);

        TicketBuilder builder = new TicketBuilder(scannerManager.getScanner());
        Ticket ticket = builder.build();

        if (collectionManager.getCollection().isEmpty()) {
            try {
                collectionManager.add(ticket);
                System.out.println("Added Ticket with price " + ticket.getPrice() + " to collection");
                return;
            } catch (DuplicateIdException e) {
                System.out.println(e.getMessage());
                System.out.println("Ticket not added");
            }
        }

        Ticket minTicket = Collections.min(collectionManager.getCollection());
        if (ticket.compareTo(minTicket) < 0) {
            try {
                collectionManager.add(ticket);
                System.out.println("Added Ticket with price " + ticket.getPrice() + " to collection");
                return;
            } catch (DuplicateIdException e) {
                System.out.println(e.getMessage());
                System.out.println("Ticket not added");
            }
        }

        System.out.println("Ticket not added to collection. Current min price is " + minTicket.getPrice());
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Adds ticket to collection if his price is less than min price.";
    }
}
