package src.managers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import src.models.*;

public class CollectionManager {
    private Set<Ticket> collection = new HashSet<>();
    private final Set<Long> usedIds = new HashSet<>();
    private final LocalDateTime initTime;

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

    public void setCollection(Set<Ticket> collection) {
        this.collection = collection;
    }

    public int getCollectionSize() {
        return collection.size();
    }

    public Set<Ticket> getCollection() {
        return this.collection;
    }

    public LocalDateTime getInitTime() {
        return this.initTime;
    }

    public String getCollectionType() {
        return this.collection.getClass().getSimpleName();
    }
}
