package src;

import src.commands.*;
import src.managers.CollectionManager;
import src.managers.CommandManager;
import src.managers.FileManager;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
    private final CommandManager commandManager = new CommandManager();
    private final CollectionManager collectionManager = new CollectionManager();
    private final FileManager fileManager = new FileManager();
    private final Scanner scanner = new Scanner(System.in);

    private String filePath;

    private boolean running = true;

    public Program() {
        commandManager.addCommand("help", new Help(commandManager));
        commandManager.addCommand("info", new Info(collectionManager, commandManager));
        commandManager.addCommand("show", new Show(collectionManager));
        commandManager.addCommand("add", new Add(collectionManager, scanner));
        commandManager.addCommand("remove_by_id", new RemoveById(collectionManager));
        commandManager.addCommand("clear", new Clear(collectionManager));
        commandManager.addCommand("save", new Save(collectionManager, fileManager, filePath));
        commandManager.addCommand("exit", new Exit(this::stop));
        commandManager.addCommand("min_by_creation_date", new MinByCreationDate(collectionManager));
    }

    public static void main(String[] args) {
        new Program().start();
    }

    private void stop() {
        running = false;
    }

    private void start() {
        filePath = System.getenv("COLLECTION_FILE");
        collectionManager.setCollection(fileManager.load(filePath));

        System.out.println("Console program started. Type 'help' for commands.");

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] args = input.split("\\s+");

            Command command = commandManager.getCommand(args[0]);
            args = Arrays.copyOfRange(args, 1, args.length);
            if (command != null) {
                command.execute(args);
            } else {
                System.out.println("Unknown command. Type 'help' for available commands");
            }
        }

        scanner.close();
    }
}
