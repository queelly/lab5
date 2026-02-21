package commands;

import manager.CollectionManager;
import manager.PrinterManager;

public class ShowCommand implements Executable {

    private PrinterManager printerManager = new PrinterManager();
    private CollectionManager collection;

    public ShowCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public int execute(String... args) {
        if (args.length != 0) {
            printerManager.printErr("Command does not accept args!");
            return 1;
        }
        printerManager.println(collection.getCollectionAsString());
        return 0;
    }

    @Override
    public String toString() {
        return ": show all Collection's elements";
    }
}
