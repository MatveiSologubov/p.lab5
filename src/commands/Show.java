package src.commands;

import src.managers.CollectionManager;
import src.models.Ticket;

import java.util.Set;

public class Show extends Command{
    private final CollectionManager collectionManager;
    public Show(CollectionManager manger){
       this.collectionManager = manger;
    }

    /**
     *
     */
    @Override
    public void execute() {
        Set<Ticket> tickets = collectionManager.getCollection();
        for(Ticket ticket : tickets){
            System.out.println(ticket);
        }
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "This command will show current collection";
    }
}
