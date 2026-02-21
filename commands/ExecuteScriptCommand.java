package commands;

import manager.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ExecuteScriptCommand implements Executable {

    private PrinterManager printerManager = new PrinterManager();
    private CommandManager commandManager;
    private FileManager fileManager;

    public ExecuteScriptCommand(CommandManager commandManager, FileManager fileManager) {
        this.commandManager = commandManager;
        this.fileManager = fileManager;
    }

    @Override
    public int execute(String... args) {
        if (args.length == 0) {
            printerManager.printErr("Missing args!");
            return 1;
        }
        if (args.length > 1) {
            printerManager.printErr("Too much args! Must be 1!");
            return 1;
        }
        String fileName = args[0];
        File file = new File(fileName);
        if (!file.exists()) {
            printerManager.printErr("File " + fileName + " doesn't exist!");
            return 1;
        }
        if (!file.canRead()) {
            printerManager.printErr("Can't read file " + fileName + "!");
            return 1;
        }
        try {
            StackManager.pushToStack(file);
            ScannerManager scannerManager = new ScannerManager(new Scanner(file));
            while (true) {
                try {
                    String inputLine = scannerManager.readLine();
                    String[] command = inputLine.trim().split("\\s+");
                    if (command.length == 0 || command[0].isEmpty()) {
                        continue;
                    }
                    String[] newArgs = Arrays.copyOfRange(command, 1, command.length);
                    if (command[0].equalsIgnoreCase("execute_script") &&
                            newArgs.length == 1) {
                        File newFile = new File(newArgs[0]);
                        if (file.exists() && file.canRead() && StackManager.isRecursive(newFile)) {
                            printerManager.printErr("Recursion was occurred with file " + fileName + "!");
                            return 1;
                        }
                    }
                    commandManager.executeCommand(command[0].toLowerCase(), newArgs, scannerManager);
                } catch (NoSuchElementException e) {
                    return 0;
                }
            }
        } catch (FileNotFoundException e) {
            printerManager.printErr("File " + fileName + " doesn't exist!");
            return 1;
        } finally {
            StackManager.popFromStack();
        }
    }

    @Override
    public String toString() {
        return ": read and execute the script from the specified file. " +
                "The script contains commands in the same form as they are " +
                "entered by the user interactively.";
    }
}
