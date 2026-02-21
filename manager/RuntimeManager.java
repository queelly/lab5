package manager;

import commands.ExitCommand;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class RuntimeManager {

    private ScannerManager scannerManager;
    private PrinterManager printerManager = new PrinterManager();
    private FileManager fileManager;
    private CollectionManager collectionManager;
    private CommandManager commandManager;
    private static boolean saveBeforeExit = true;

    public RuntimeManager(
            ScannerManager scannerManager,
            FileManager fileManager,
            CollectionManager collectionManager,
            CommandManager commandManager
    ) {
        this.scannerManager = scannerManager;
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public static void setSaveBeforeExit(boolean saveBeforeExit) {
        RuntimeManager.saveBeforeExit = saveBeforeExit;
    }

    public void interactiveMode() {
        fileManager.loadCollectionFromCSV(collectionManager);
        printerManager.println("Welcome to professional DataBase manager powered by ITMO University!");
        printerManager.println("Please, type \"help\" to get help with manager");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (saveBeforeExit) {
                if (fileManager.writeCollectionToCSV(collectionManager) == 0) {
                    printerManager.println("Collection was successfully saved to " +
                            fileManager.getFileName() + " before exit");
                } else {
                    printerManager.printErr("Collection was not saved before exit.");
                }
            }
        }));
        while (true) {
            try {
                String inputLine = scannerManager.readLine();
                String[] command = inputLine.trim().split("\\s+");
                if (command.length == 0 || command[0].isEmpty()) {
                    continue;
                }
                String[] args = Arrays.copyOfRange(command, 1, command.length);
                commandManager.executeCommand(command[0].toLowerCase(), args, scannerManager);
            } catch (NoSuchElementException e) {
                return;
            }
        }
    }
}
