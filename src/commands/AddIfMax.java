package src.commands;

import src.exceptions.DuplicateIdException;
import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.managers.ScannerManager;
import src.models.Ticket;
import src.models.builders.TicketBuilder;

import java.util.Collections;

public class AddIfMax extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public AddIfMax(CollectionManager collectionManager, ScannerManager scannerManager) {
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
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException(0, args.length);

            TicketBuilder ticketBuilder = new TicketBuilder(scannerManager.getScanner());
            Ticket ticket = ticketBuilder.build();

            Ticket maxTicket = Collections.max(collectionManager.getCollection());
            if (collectionManager.getCollection().isEmpty()) {
                collectionManager.add(ticket);
                System.out.println("Added Ticket with price " + ticket.getPrice() + " to collection");
                return;
            }

            if (ticket.compareTo(maxTicket) > 0) {
                collectionManager.add(ticket);
                System.out.println("Added Ticket with price " + ticket.getPrice() + " to collection");
                return;
            }
            System.out.println("Ticket not added to collection. Current max price is " + maxTicket.getPrice());
        } catch (DuplicateIdException e) {
            System.out.println(e.getMessage());
            System.out.println("Ticket not added");
        } catch (WrongAmountOfArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Adds ticket to collection if his price is greater than max price.";
    }
}
