package commands;

import manager.CollectionManager;
import manager.PrinterManager;

public class InfoCommand implements Executable {

    private PrinterManager printerManager = new PrinterManager();
    private CollectionManager collection;

    public InfoCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public int execute(String... args) {
        if (args.length != 0) {
            printerManager.printErr("Command does not accept args!");
            return 1;
        }
        printerManager.println("Initialization time: " + collection.getInitializationDateTime());
        printerManager.println("Collection type: " + collection.getCollectionType());
        printerManager.println("Current Collection size: " + collection.getCollectionSize());
        printerManager.println("Collection's elements:");
        printerManager.print(collection.getCollectionAsString());
        return 0;
    }

    @Override
    public String toString() {
        return ": print all info about collection";
    }
}
