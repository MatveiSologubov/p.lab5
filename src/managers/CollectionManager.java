package src.managers;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import src.models.*;
import javax.xml.stream.*;
import java.io.*;

public class CollectionManager {
    private Set<Ticket> collection = new HashSet<>();

    public CollectionManager(){}

    public CollectionManager(Set<Ticket> collection){
        this.collection = collection;
    }

    public void add(Ticket ticket){
        collection.add(ticket);
    }

    public void showInfo(){
        Ticket[] tickets = collection.toArray(new Ticket[0]);

        for (Ticket ticket : tickets){
            System.out.println(ticket);
        }
    }

    public void setCollection(Set<Ticket> collection){
        this.collection = collection;
    }

    public Set<Ticket> getCollection(){
        return this.collection;
    }
}
