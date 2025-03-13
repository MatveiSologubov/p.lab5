package src;

import src.commands.*;
import src.managers.CollectionManager;
import src.managers.CommandManager;
import src.models.Coordinates;
import src.models.Person;
import src.models.Ticket;
import src.models.TicketType;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Program {
	private final CommandManager commandManager = new CommandManager();
	private final CollectionManager collectionManager = new CollectionManager();
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
		try {
			// Save
			//collectionManager.saveToFile("tickets.xml");

			// Load
			collectionManager.loadFromFile("tickets.xml");
			collectionManager.showInfo();

			Person person = new Person(LocalDateTime.now(), 12, 12.3F, "ABCD");
			Ticket ticket = new Ticket("Test", new Coordinates(12, 12.3F), ZonedDateTime.now(), 12.3F, "damn", true, TicketType.USUAL, person);

			collectionManager.add(ticket);
			collectionManager.saveToFile("tickets.xml");
		} catch (IOException | XMLStreamException e) {
			throw new RuntimeException(e);
		}
//		System.out.println("Console program started. Type 'help' for src.commands.");
//
//		while(running){
//			System.out.print("> ");
//			String input = scanner.nextLine().trim();
//
//			Command command = commandManager.getCommand(input);
//			if(command != null){
//				command.execute();
//			} else if (!input.isEmpty()) {
//				System.out.println("Unknown command. Type 'help' for available src.commands");
//			}
//		}
//
//		scanner.close();
	}

	public static void main(String[] args) {
		new Program().start();
	}
}
