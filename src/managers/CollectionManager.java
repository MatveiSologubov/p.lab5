package src.managers;

import src.models.Ticket;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class CollectionManager {
    private final Set<Long> usedIds = new HashSet<>();
    private final LocalDateTime initTime;
    private Set<Ticket> collection = new HashSet<>();

    public CollectionManager() {
        this.initTime = LocalDateTime.now();
    }

    public void add(Ticket ticket) {
        final long id = ticket.getId();
        if (usedIds.contains(id)) {
            System.out.println("Id is already in use");
            return;
        }
        usedIds.add(id);
        collection.add(ticket);
    }

    public void clearCollection() {
        collection.clear();
    }

    public int getCollectionSize() {
        return collection.size();
    }

    public Set<Ticket> getCollection() {
        return this.collection;
    }

    public void setCollection(Set<Ticket> collection) {
        this.collection = collection;
    }

    public LocalDateTime getInitTime() {
        return this.initTime;
    }

    public String getCollectionType() {
        return this.collection.getClass().getSimpleName();
    }
}
