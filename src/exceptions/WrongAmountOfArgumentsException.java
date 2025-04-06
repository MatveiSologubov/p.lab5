package src.exceptions;

public class WrongAmountOfArgumentsException extends RuntimeException {
    public WrongAmountOfArgumentsException(int expected, int actual) {
        super("ERROR: Expected " + expected + " arguments but got " + actual);
    }
}
