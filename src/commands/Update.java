package src.commands;

import src.managers.CollectionManager;
import src.managers.ScannerManager;
import src.models.Ticket;
import src.models.builders.TicketBuilder;

public class Update extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public Update(CollectionManager collectionManager, ScannerManager scannerManager) {
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
        if (args.length != 1) {
            System.out.println("Wrong number of arguments");
            return;
        }
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be an integer");
            return;
        }

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
            System.out.println("Ticket does not exist");
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
