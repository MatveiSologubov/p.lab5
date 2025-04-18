package src;

import src.commands.*;
import src.managers.CollectionManager;
import src.managers.CommandManager;
import src.managers.FileManager;
import src.managers.ScannerManager;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
    private final CommandManager commandManager = new CommandManager();
    private final CollectionManager collectionManager = new CollectionManager();
    private final FileManager fileManager = new FileManager();
    private final Scanner scanner = new Scanner(System.in);

    private final String filePath;

    private boolean running = true;

    public Program() {
        filePath = System.getenv("COLLECTION_FILE");
        if (filePath == null) {
            System.out.println("File path cannot be null");
            System.exit(0);
        }
        ScannerManager scannerManager = new ScannerManager(scanner);

        commandManager.addCommand("help", new Help(commandManager));
        commandManager.addCommand("info", new Info(collectionManager));
        commandManager.addCommand("show", new Show(collectionManager));
        commandManager.addCommand("add", new Add(collectionManager, scannerManager));
        commandManager.addCommand("update", new Update(collectionManager, scannerManager));
        commandManager.addCommand("remove_by_id", new RemoveById(collectionManager));
        commandManager.addCommand("clear", new Clear(collectionManager));
        commandManager.addCommand("save", new Save(collectionManager, fileManager, filePath));
        commandManager.addCommand("execute_script", new ExecuteScript(commandManager, scannerManager));
        commandManager.addCommand("exit", new Exit(this::stop));
        commandManager.addCommand("add_if_max", new AddIfMax(collectionManager, scannerManager));
        commandManager.addCommand("add_if_min", new AddIfMin(collectionManager, scannerManager));
        commandManager.addCommand("remove_greater", new RemoveGreater(collectionManager, scannerManager));
        commandManager.addCommand("min_by_creation_date", new MinByCreationDate(collectionManager));
        commandManager.addCommand("filter_less_than_type", new FilterLessThanType(collectionManager));
        commandManager.addCommand("filter_greater_than_price", new FilterGreaterThanPrice(collectionManager));
    }

    public static void main(String[] args) {
        new Program().start();
    }

    private void stop() {
        running = false;
    }

    private void start() {
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
                try {
                    command.execute(args);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Unknown command. Type 'help' for available commands");
            }
        }
        scanner.close();
    }
}
