package src.commands;

import src.exceptions.CollectionIsEmptyException;
import src.exceptions.ElementNotFoundException;
import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.managers.ScannerManager;
import src.models.Ticket;
import src.models.builders.TicketBuilder;

/**
 * 'Update' command builds Ticket and the updates Ticket with specified id with data from new Ticket
 */
public class Update extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public Update(CollectionManager collectionManager, ScannerManager scannerManager) {
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    /**
     * execute command
     * @param args arguments for command
     * @throws WrongAmountOfArgumentsException if user provides wrong amount of arguments
     */
    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        try {
            if (args.length != 1) throw new WrongAmountOfArgumentsException(1, args.length);
            if (collectionManager.getCollection().isEmpty()) throw new CollectionIsEmptyException();

            int id = Integer.parseInt(args[0]);

            boolean ticketExists = false;
            for (Ticket ticket : collectionManager.getCollection()) {
                if (ticket.getId() == id) {
                    ticketExists = true;
                    TicketBuilder ticketBuilder = new TicketBuilder(scannerManager.getScanner());
                    ticket.update(ticketBuilder.build());
                    System.out.println("Ticket updated");
                    break;
                }
            }

            if (!ticketExists) {
                throw new ElementNotFoundException("Ticket with id " + id);
            }
        } catch (CollectionIsEmptyException | ElementNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be an integer");
        }
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Changes element with specified id. Usage: update id {element}";
    }
}
