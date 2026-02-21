package commands;

import manager.CollectionManager;
import manager.PrinterManager;

public class ClearCommand implements Executable {

    private PrinterManager printerManager = new PrinterManager();
    private CollectionManager collection;

    public ClearCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public int execute(String... args) {
        if (args.length != 0) {
            printerManager.printErr("Command does not accept args!");
            return 1;
        }
        if (collection.clear()) {
            printerManager.println("Collection was cleared successfully!");
            return 0;
        } else {
            printerManager.println("Collection was not cleared(");
            return -1;
        }
    }

    @Override
    public String toString() {
        return ": clear Collection";
    }
}
