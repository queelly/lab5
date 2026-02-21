package manager;

import commands.Executable;

import java.util.HashMap;

public class CommandManager {

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
}
