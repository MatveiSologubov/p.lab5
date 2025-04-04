package src.commands;

import src.managers.CommandManager;
import src.managers.ScannerManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScript extends Command {
    private final static Set<String> runningScripts = new HashSet<>();
    private final CommandManager commandManager;
    private final ScannerManager scannerManager;

    public ExecuteScript(CommandManager commandManager, ScannerManager scannerManager) {
        this.commandManager = commandManager;
        this.scannerManager = scannerManager;
    }

    /**
     * execute command
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect number of arguments");
            return;
        }

        String filePath = args[0];
        File scriptFile = new File(filePath);
        if (runningScripts.contains(filePath)) {
            System.out.println("Error: Recursion detected");
            return;
        }
        runningScripts.add(filePath);

        try (Scanner scriptScanner = new Scanner(scriptFile)) {
            scannerManager.setScriptScanner(scriptScanner);
            scannerManager.setScriptMode(true);

            while (scriptScanner.hasNextLine()) {
                String input = scriptScanner.nextLine().trim();
                if (input.isEmpty()) continue;

                String[] parts = input.split("\\s+");
                String commandName = parts[0];
                String[] arguments = Arrays.copyOfRange(parts, 1, parts.length);

                Command command = commandManager.getCommand(commandName);
                if (command != null) {
                    command.execute(arguments);
                    continue;
                }
                System.out.println("Unknown command: " + commandName);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        } finally {
            runningScripts.remove(filePath);
            scannerManager.setScriptMode(false);
        }
    }

    /**
     * @return Help message
     */
    @Override
    public String getHelp() {
        return "Executes a script file. Usage: execute_script file_name";
    }
}
