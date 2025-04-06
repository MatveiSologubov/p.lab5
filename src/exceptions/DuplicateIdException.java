package src.exceptions;

public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException(long id) {
        super("ERROR: ID: " + id + " is already in use");
    }
}
