package src.commands;

import src.managers.CollectionManager;
import src.models.Ticket;

import java.util.Comparator;
import java.util.Set;

public class MinByCreationDate extends Command {
    private final CollectionManager collectionManager;

    public MinByCreationDate(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
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

        Set<Ticket> collection = collectionManager.getCollection();

        if (collection.isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }

        Ticket minTicket = collection.stream()
                .min(Comparator.comparing(Ticket::getCreationDate))
                .orElse(null);
        System.out.println(minTicket);
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Shows element with minimum creation date";
    }
}
