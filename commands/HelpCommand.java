package commands;

import manager.CommandManager;
import manager.PrinterManager;

public class HelpCommand implements Executable {

    private PrinterManager printerManager = new PrinterManager();
    private CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public int execute(String... args) {
        if (args.length != 0) {
            printerManager.printErr("Command does not accept args!");
            return 1;
        }
        printerManager.println("Here are all available commands:");
        commandManager.getCommands().forEach(
                (commandName, command) ->
                        printerManager.println(commandName + command)
        );
        return 0;
    }

    @Override
    public String toString() {
        return ": show information about all commands";
    }
}
