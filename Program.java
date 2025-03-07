import java.util.HashMap;
import java.util.Scanner;

public class Program {
	interface Command {
		void execute();
		String getHelp();
	}

	private final HashMap<String, Command> commands = new HashMap<>();
	private final Scanner scanner = new Scanner(System.in);

	private boolean running = true;

	public Program(){
		addCommand("help", new HelpCommand());
		addCommand("exit", new ExitCommand());
		addCommand("greet", new GreetCommand());
	}

	public void addCommand(String name, Command command){
		commands.put(name, command);
	}

	private class HelpCommand implements Command {
		@Override
		public void execute() {
			System.out.println("Available commands:");
			commands.forEach((key, value) ->
					System.out.printf("  %-10s%s%n", key, value.getHelp()));
		}

		@Override
		public String getHelp() {
			return "Shows this help message";
		}
	}

	private class ExitCommand implements Command {
		@Override
		public void execute() {
			System.out.println("Exiting program...");
			running = false;
		}

		@Override
		public String getHelp() {
			return "Exits the program";
		}
	}

	private class GreetCommand implements Command {
		@Override
		public void execute() {
			System.out.println("Hello! Welcome to the console program!");
		}

		@Override
		public String getHelp() {
			return "Displays a greeting message";
		}
	}

	private void start(){
		System.out.println("Console program started. Type 'help' for available commands");

		while(running){
			System.out.println("> ");
			String input = scanner.nextLine().trim().toLowerCase();

			Command command = commands.get(input);
			if(command != null){
				command.execute();
			} else if (!input.isEmpty()) {
				System.out.println("Unknown command. Type 'help' for available commands.");
			}
		}

		scanner.close();
	}

	public static void main(String[] args) {
		new Program().start();
	}
}
