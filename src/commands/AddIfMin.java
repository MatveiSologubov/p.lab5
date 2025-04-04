package src.commands;

import src.managers.CollectionManager;
import src.managers.ScannerManager;
import src.models.Ticket;
import src.models.builders.TicketBuilder;

import java.util.Collections;

public class AddIfMin extends Command{
    CollectionManager collectionManager;
    ScannerManager scannerManager;

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
    public void execute(String[] args) {
        TicketBuilder builder = new TicketBuilder(scannerManager.getScanner());
        Ticket ticket = builder.buildTicket();

        if (collectionManager.getCollection().isEmpty()){
            collectionManager.add(ticket);
            System.out.println("Added Ticket with price " + ticket.getPrice() + " to collection");
            return;
        }

        Ticket minTicket = Collections.min(collectionManager.getCollection());
        if (ticket.compareTo(minTicket) < 0){
            collectionManager.add(ticket);
            System.out.println("Added Ticket with price " + ticket.getPrice() + " to collection");
            return;
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
