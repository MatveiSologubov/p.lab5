package src.exceptions;

public class DuplicateIdException extends Exception {
    public DuplicateIdException(long id) {
        super("ERROR: ID: " + id + " is already in use");
    }
}
