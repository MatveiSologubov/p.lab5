package src.commands;

import src.managers.CollectionManager;
import src.models.Ticket;

public class FilterGreaterThanPrice extends Command {
    CollectionManager collectionManager;

    public FilterGreaterThanPrice(CollectionManager collectionManager) {
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

        int price;
        try {
            price = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format");
            return;
        }

        for (Ticket ticket : collectionManager.getCollection()) {
            if (ticket.getPrice() > price) {
                System.out.println(ticket);
            }
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
