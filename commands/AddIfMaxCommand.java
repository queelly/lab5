package commands;

import manager.CollectionManager;
import manager.PrinterManager;
import manager.ScannerManager;
import models.builders.WorkerBuilder;

public class AddIfMaxCommand implements Executable {

    private ScannerManager scannerManager;
    private PrinterManager printerManager = new PrinterManager();
    private CollectionManager collection;

    public AddIfMaxCommand(CollectionManager collection, ScannerManager scannerManager) {
        this.collection = collection;
        this.scannerManager = scannerManager;
    }

    @Override
    public int execute(String... args) {
        if (args.length != 0) {
            printerManager.printErr("Command does not accept args!");
            return 1;
        }
        if (collection.addIfMax(new WorkerBuilder(scannerManager).build())) {
            printerManager.println("New worker was added successfully!");
            return 0;
        } else {
            printerManager.println("New worker was not added(");
            return -1;
        }
    }

    @Override
    public String toString() {
        return ": add new Worker to Collection " +
                "in case the value of Worker is more than maximal value in Collection";
    }
}
