package src.commands;

import src.exceptions.CollectionIsEmptyException;
import src.exceptions.ElementNotFoundException;
import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.models.Ticket;

/**
 * 'Remove By ID' command removes Ticket with specified id
 */
public class RemoveById extends Command {
    final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * execute command
     * @param args id of ticket to be removed
     */
    @Override
    public void execute(String[] args) {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException(1, args.length);
            if (collectionManager.getCollection().isEmpty()) {
                throw new CollectionIsEmptyException();
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
                throw new ElementNotFoundException("Ticket with id " + id);
            }

            System.out.println("Ticket with id " + id + " removed");
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be an integer.");
        } catch (CollectionIsEmptyException | ElementNotFoundException | WrongAmountOfArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Removes element from collection with specified id";
    }
}
