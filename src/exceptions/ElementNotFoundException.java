package src.exceptions;

/**
 * throws if element could not be finded
 */
public class ElementNotFoundException extends Exception {
    public ElementNotFoundException(String message) {
        super("ERROR: " + message + " not found");
    }
}
