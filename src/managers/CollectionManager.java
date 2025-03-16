package src.managers;

import java.util.HashSet;
import java.util.Set;
import src.models.*;

public class CollectionManager {
    private Set<Ticket> collection = new HashSet<>();

    public CollectionManager(){}

    public CollectionManager(Set<Ticket> collection){
        this.collection = collection;
    }

    public void add(Ticket ticket){
        collection.add(ticket);
    }

    public void setCollection(Set<Ticket> collection){
        this.collection = collection;
    }

    public Set<Ticket> getCollection(){
        return this.collection;
    }
}
