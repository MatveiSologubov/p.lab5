package src.commands;

import src.managers.CollectionManager;
import src.models.Ticket;

public class RemoveById extends Command {
    final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * execute command
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong number of arguments");
            return;
        }
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Collection is empty");
        }

        int id = Integer.parseInt(args[0]);
        boolean success = false;
        for (Ticket ticket : collectionManager.getCollection()) {
            if (ticket.getId() == id) {
                collectionManager.getCollection().remove(ticket);
                success = true;
                break;
            }
        }
        if (!success) {
            System.out.println("Ticket not found");
            return;
        }

        System.out.println("Ticket with id " + id + " removed");
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Removes element from collection with specified id";
    }
}
