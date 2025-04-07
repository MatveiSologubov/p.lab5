package src.commands;

import src.exceptions.CollectionIsEmptyException;
import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.models.Ticket;

/**
 * 'Filter Greater Than Price' command prints every Ticket which has price greater than specified
 */
public class FilterGreaterThanPrice extends Command {
    private final CollectionManager collectionManager;

    public FilterGreaterThanPrice(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * execute command
     * @param args price to filter
     */
    @Override
    public void execute(String[] args) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException(1, args.length);
            if (collectionManager.getCollection().isEmpty()) {
                throw new CollectionIsEmptyException();
            }

            float price;
            price = Float.parseFloat(args[0]);
            for (Ticket ticket : collectionManager.getCollection()) {
                float currentPrice = 0;
                if (ticket.getPrice() != null) {
                    currentPrice = ticket.getPrice();
                }

                if (Float.compare(currentPrice, price) > 0) {
                    System.out.println(ticket);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Wrong number format");
        } catch (WrongAmountOfArgumentsException | CollectionIsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Prints all the tickets that have price greater than the specified price.";
    }
}
