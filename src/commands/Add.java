package src.commands;

import src.managers.CollectionManager;
import src.models.Ticket;
import src.models.builders.TicketBuilder;

import java.util.Scanner;

public class Add extends Command {
    private final CollectionManager collectionManager;
    private final Scanner scanner;

    public Add(CollectionManager collectionManager, Scanner scaner) {
        this.collectionManager = collectionManager;
        this.scanner = scaner;
    }

    /**
     * execute command
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Starting Ticket Builder...");
        TicketBuilder builder = new TicketBuilder(scanner);
        Ticket ticket = builder.buildTicket();
        collectionManager.add(ticket);
        System.out.println("Ticket added.");
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Adds element to collection; Usage add {element}";
    }
}
