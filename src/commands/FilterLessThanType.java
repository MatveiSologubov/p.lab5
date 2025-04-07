package src.commands;

import src.exceptions.CollectionIsEmptyException;
import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.models.Ticket;
import src.models.TicketType;

import java.util.Arrays;

/**
 * 'Filter Less Than Type' prints all ticket which have TicketType less than specified
 */
public class FilterLessThanType extends Command {
    private final CollectionManager collectionManager;

    public FilterLessThanType(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * execute command
     * @param args TicketType to filter
     * @throws WrongAmountOfArgumentsException if user provides wrong amount of arguments
     */
    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        if (args.length != 1) throw new WrongAmountOfArgumentsException(1, args.length);
        try {
            if (collectionManager.getCollection().isEmpty()) throw new CollectionIsEmptyException();

            System.out.println("Order: " + TicketType.order());

            TicketType type;
            type = TicketType.valueOf(args[0].toUpperCase());

            for (Ticket ticket : collectionManager.getCollection()) {
                if (ticket.getType() == null || ticket.getType().compareTo(type) < 0) {
                    System.out.println(ticket);
                }
            }
        } catch (CollectionIsEmptyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Wrong type format");
            System.out.println("Allowed types are: " + Arrays.toString(TicketType.values()));
        }
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Prints all the tickets that have type \"smaller\" than the specified type.";
    }
}