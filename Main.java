import commands.*;
import manager.*;
import models.builders.WorkerBuilder;

public class Main {
    public static void main(String[] args) {
        PrinterManager printerManager = new PrinterManager();
        ScannerManager scannerManager = new ScannerManager();
        CollectionManager collectionManager = new CollectionManager();
        String fileName = "";
        try {
            fileName = args[0];
        } catch (IndexOutOfBoundsException e) {
            printerManager.printErr("No file name as argument!");
        }
        FileManager fileManager = new FileManager(fileName);
        CommandManager commandManager = new CommandManager();
        commandManager.addCommand("add",
                new AddCommand(collectionManager, scannerManager));
        commandManager.addCommand("add_if_max",
                new AddIfMaxCommand(collectionManager, scannerManager));
        commandManager.addCommand("add_if_min",
                new AddIfMinCommand(collectionManager, scannerManager));
        commandManager.addCommand("clear",
                new ClearCommand(collectionManager));
        commandManager.addCommand("execute_script",
                new ExecuteScriptCommand(commandManager, fileManager));
        commandManager.addCommand("exit",
                new ExitCommand());
        commandManager.addCommand("filter_less_than_position",
                new FilterLessThanPositionCommand(collectionManager));
        commandManager.addCommand("filter_starts_with_name",
                new FilterStartsWithNameCommand(collectionManager));
        commandManager.addCommand("help",
                new HelpCommand(commandManager));
        commandManager.addCommand("info",
                new InfoCommand(collectionManager));
        commandManager.addCommand("print_field_ascending_salary",
                new PrintFieldAscendingSalaryCommand(collectionManager));
        commandManager.addCommand("remove_by_id",
                new RemoveByIdCommand(collectionManager));
        commandManager.addCommand("remove_head",
                new RemoveHeadCommand(collectionManager));
        commandManager.addCommand("save",
                new SaveCommand(collectionManager));
        commandManager.addCommand("show",
                new ShowCommand(collectionManager));
        new RuntimeManager(scannerManager, fileManager, collectionManager, commandManager).interactiveMode();
    }
}