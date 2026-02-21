package commands;

import manager.CollectionManager;
import manager.PrinterManager;

public class RemoveByIdCommand implements Executable {

    private PrinterManager printerManager = new PrinterManager();
    private CollectionManager collection;

    public RemoveByIdCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public int execute(String... args) {
        if (args.length != 0) {
            printerManager.printErr("Command does not accept args!");
            return 1;
        }
        if (collection.getCollectionSize() == 0) {
            printerManager.println("Collection is empty!");
            return -1;
        } else {
            printerManager.println("Removed element:");
            printerManager.println(collection.removeHead());
        }
        return 0;
    }

    @Override
    public String toString() {
        return ": remove Worker from the head of Collection";
    }
}
