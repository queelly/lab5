package manager;

import commands.Executable;
import utility.ScannerUsing;

import java.util.HashMap;

public class CommandManager {

    private ScannerManager scannerManager;
    private PrinterManager printerManager = new PrinterManager();
    private HashMap<String, Executable> commands = new HashMap<>();

    public void addCommand(String commandName, Executable command) {
        commands.put(commandName, command);
    }

    public HashMap<String, Executable> getCommands() {
        return this.commands;
    }

    public void executeCommand(String commandName, String[] args) {
        Executable command = commands.get(commandName);
        if (command == null) {
            printerManager.printErr("That command doesn't exist!");
        } else {
            command.execute(args);
        }
    }

    public void executeCommand(String commandName, String[] args, ScannerManager scannerManager) {
        Executable command = commands.get(commandName);
        if (command == null) {
            printerManager.printErr("That command doesn't exist!");
            return;
        }
        if (!(command instanceof ScannerUsing)) {
            this.executeCommand(commandName, args);
        } else {
            ((ScannerUsing) command).setScannerManager(scannerManager);
            command.execute(args);
            ((ScannerUsing) command).setScannerManager(new ScannerManager());
        }
    }
}
