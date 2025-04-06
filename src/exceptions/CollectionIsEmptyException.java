package src.exceptions;

public class CollectionIsEmptyException extends Exception {
    public CollectionIsEmptyException() {
        super("ERROR: Collection is empty");
    }
}
