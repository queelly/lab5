package commands;

import manager.CollectionManager;
import manager.PrinterManager;
import models.Worker;

import java.util.List;

public class FilterStartsWithNameCommand implements Executable {

    private PrinterManager printerManager = new PrinterManager();
    private CollectionManager collection;

    public FilterStartsWithNameCommand(CollectionManager collection) {
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
        String name = args[0];
        List<Worker> filteredWorkers = collection.filterStartsWithName(name);
        if (filteredWorkers.isEmpty()) {
            printerManager.println("Result is empty!");
        } else {
            printerManager.println("Filtered result:");
            for (Worker worker : filteredWorkers) {
                printerManager.println(worker);
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return ": print Workers whose Name starts with specified one";
    }
}
