package src.commands;

import src.managers.CollectionManager;
import src.models.Ticket;
import src.models.builders.TicketBuilder;

import java.util.Collections;
import java.util.Scanner;

public class AddIfMin extends Command{
    CollectionManager collectionManager;
    Scanner scanner;

    public AddIfMin(CollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    /**
     * execute command
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        TicketBuilder builder = new TicketBuilder(scanner);
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
