package src;

import src.commands.*;
import src.managers.CollectionManager;
import src.managers.CommandManager;
import src.managers.FileManager;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Scanner;

public class Program {
	private final CommandManager commandManager = new CommandManager();
	private final CollectionManager collectionManager = new CollectionManager();
	private final FileManager fileManager = new FileManager();
	private final Scanner scanner = new Scanner(System.in);

	private boolean running = true;

	public Program(){
		commandManager.addCommand("help", new Help(commandManager));
		commandManager.addCommand("exit", new Exit(this::stop));
		commandManager.addCommand("info", new Info(collectionManager, commandManager));
		commandManager.addCommand("show", new Show(collectionManager));
	}

	private void stop() {
		running = false;
	}

	private void start(){
		try {
			collectionManager.setCollection(fileManager.load("tickets.xml"));
		} catch (IOException | XMLStreamException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Console program started. Type 'help' for src.commands.");

		while(running){
			System.out.print("> ");
			String input = scanner.nextLine().trim();

			Command command = commandManager.getCommand(input);
			if(command != null){
				command.execute();
			} else if (!input.isEmpty()) {
				System.out.println("Unknown command. Type 'help' for available src.commands");
			}
		}

		scanner.close();
	}

	public static void main(String[] args) {
		new Program().start();
	}
}
