package src.exceptions;

public class FieldMustNotBeEmpty extends Exception {
    public FieldMustNotBeEmpty(String message) {
        super("ERROR: " + message + "must not be empty");
    }
}
