package src.commands;

import src.managers.CollectionManager;
import src.models.Ticket;
import src.models.TicketType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FilterLessThanType extends Command {
    private final CollectionManager collectionManager;

    public FilterLessThanType(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * execute command
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong number of arguments");
            return;
        }
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }

        printOrder();

        TicketType type;
        try {
            type = TicketType.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong type format");
            System.out.println("Allowed types are: " + printOrder());
            return;
        }

        for (Ticket ticket : collectionManager.getCollection()) {
            if (ticket.getType() == null || ticket.getType().compareTo(type) < 0) {
                System.out.println(ticket);
            }
        }
    }

    private String printOrder() {
        String result = Arrays.stream(TicketType.values())
                .map(Enum::name)
                .collect(Collectors.joining(" < "));
        return "Order: " + result;
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Prints all the tickets that have type \"smaller\" than the specified type. " + printOrder();
    }
}