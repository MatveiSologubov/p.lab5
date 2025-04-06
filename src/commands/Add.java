package src.commands;

import src.exceptions.DuplicateIdException;
import src.exceptions.WrongAmountOfArgumentsException;
import src.managers.CollectionManager;
import src.managers.ScannerManager;
import src.models.Ticket;
import src.models.builders.TicketBuilder;

public class Add extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public Add(CollectionManager collectionManager, ScannerManager scannerManager) {
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    /**
     * execute command
     *
     * @param args arguments for command
     */
    @Override
    public void execute(String[] args) {
        try {
            if (args.length != 0) throw new WrongAmountOfArgumentsException(0, args.length);
            System.out.println("Starting Ticket Builder...");
            TicketBuilder builder = new TicketBuilder(scannerManager.getScanner());
            Ticket ticket = builder.build();
            collectionManager.add(ticket);
            System.out.println("Ticket added.");
        } catch (DuplicateIdException e) {
            System.out.println(e.getMessage());
            System.out.println("Ticket not added.");
        } catch (WrongAmountOfArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Adds element to collection; Usage add {element}";
    }
}
