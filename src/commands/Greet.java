package src.commands;

public class Greet extends Command{
    @Override
    public void execute() {
        System.out.println("Hello! Welcome to the console program!");
    }

    @Override
    public String getHelp() {
        return "Displays a greeting message";
    }
}
