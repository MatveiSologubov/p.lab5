package src.exceptions;

public class DuplicateIdException extends Exception {
    public DuplicateIdException(long id ) {
        super("ID: " + id + " is already in use");
    }
}
