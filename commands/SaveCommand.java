package commands;

import manager.CollectionManager;
import manager.FileManager;
import manager.PrinterManager;

public class SaveCommand implements Executable {

    private PrinterManager printerManager = new PrinterManager();
    private CollectionManager collection;

    public SaveCommand(CollectionManager collection) {
        this.collection = collection;
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
        return new FileManager(args[0]).writeCollectionToCSV(collection);
    }

    @Override
    public String toString() {
        return ": save Collection to file";
    }
}
