package src.managers;

import src.exceptions.DuplicateIdException;
import src.models.Ticket;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class CollectionManager {
    private final Set<Long> usedIds = new HashSet<>();
    private final LocalDateTime initTime;
    private final Set<Ticket> collection = new HashSet<>();

    public CollectionManager() {
        this.initTime = LocalDateTime.now();
    }

    public void add(Ticket ticket) throws DuplicateIdException {
        final long id = ticket.getId();
        if (usedIds.contains(id)) {
            throw new DuplicateIdException(id);
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

    public void setCollection(Set<Ticket> newCollection) throws DuplicateIdException {
        collection.clear();
        usedIds.clear();
        long maxId = 0;
        for (Ticket ticket : newCollection) {
            add(ticket);
            maxId = Math.max(maxId, ticket.getId());
        }
        Ticket.setIdCounter(maxId + 1);
    }

    public LocalDateTime getInitTime() {
        return this.initTime;
    }

    public String getCollectionType() {
        return this.collection.getClass().getSimpleName();
    }
}
