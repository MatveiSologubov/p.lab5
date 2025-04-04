package src.commands;

import src.managers.CollectionManager;
import src.models.Ticket;
import src.models.builders.TicketBuilder;

import java.util.Scanner;
import java.util.Set;

public class RemoveGreater extends Command {
    CollectionManager collectionManager;
    Scanner scanner;

    public RemoveGreater(CollectionManager collectionManager, Scanner scanner) {
       this.collectionManager = collectionManager;
       this.scanner = scanner;
    }

    /**
     * execute command
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            System.out.println("Wrong number of arguments");
            return;
        }
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }

        Set<Ticket> collection = collectionManager.getCollection();
        Ticket target = new TicketBuilder(scanner).buildTicket();
        collection.removeIf(ticket -> ticket.compareTo(target) > 0);
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "";
    }
}
