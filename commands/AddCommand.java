package commands;

import manager.CollectionManager;
import manager.PrinterManager;
import manager.ScannerManager;
import models.builders.WorkerBuilder;
import utility.ScannerUsing;

public class AddCommand implements Executable, ScannerUsing {

    private ScannerManager scannerManager;
    private PrinterManager printerManager = new PrinterManager();
    private CollectionManager collection;

    @Override
    public void setScannerManager(ScannerManager scannerManager) {
        this.scannerManager = scannerManager;
    }

    public AddCommand(CollectionManager collection, ScannerManager scannerManager) {
        this.collection = collection;
        this.scannerManager = scannerManager;
    }

    @Override
    public int execute(String... args) {
        if (args.length != 0) {
            printerManager.printErr("Command does not accept args!");
            return 1;
        }
        if (collection.add(new WorkerBuilder(scannerManager).build())) {
            printerManager.println("New worker was added successfully!");
            return 0;
        } else {
            printerManager.println("New worker was not added(");
            return -1;
        }
    }

    @Override
    public String toString() {
        return ": add new Worker to Collection";
    }
}
