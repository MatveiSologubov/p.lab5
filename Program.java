import commands.*;

import java.util.Scanner;

public class Program {
	private final CommandManager commandManager = new CommandManager();
	private final Scanner scanner = new Scanner(System.in);

	private boolean running = true;

	public Program(){
		commandManager.addCommand("help", new Help(commandManager));
		commandManager.addCommand("exit", new Exit(this::stop));
		commandManager.addCommand("greet", new Greet());
	}

	private void stop() {
		running = false;
	}

	private void start(){
		System.out.println("Console program started. Type 'help' for commands.");

		while(running){
			System.out.print("> ");
			String input = scanner.nextLine().trim();

			Command command = commandManager.getCommand(input);
			if(command != null){
				command.execute();
			} else if (!input.isEmpty()) {
				System.out.println("Unknown command. Type 'help' for available commands");
			}
		}

		scanner.close();
	}

	public static void main(String[] args) {
		new Program().start();
	}
}
