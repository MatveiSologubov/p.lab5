package src.exceptions;

public class WrongAmountOfArgumentsException extends Exception {
    public WrongAmountOfArgumentsException(int expected, int actual) {
        super("ERROR: Expected " + expected + " arguments but got " + actual);
    }
}
