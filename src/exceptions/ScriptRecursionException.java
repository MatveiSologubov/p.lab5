package src.exceptions;

public class ScriptRecursionException extends Exception {
    public ScriptRecursionException() {
        super("ERROR: Script recursion detected");
    }
}
