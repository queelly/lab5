package commands;

import manager.CollectionManager;
import manager.PrinterManager;
import models.Position;
import models.Worker;

import java.util.List;

public class FilterLessThanPositionCommand implements Executable {

    private PrinterManager printerManager = new PrinterManager();
    private CollectionManager collection;

    public FilterLessThanPositionCommand(CollectionManager collection) {
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
        Position position = null;
        for (Position element : Position.values()) {
            if (args[0].equalsIgnoreCase(element.name())) {
                position = element;
                break;
            }
        }
        if (position == null) {
            printerManager.println("Invalid Position value!");
            return -1;
        }
        List<Worker> filteredWorkers = collection.filterLessThanPosition(position);
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
        return ": print Workers whose Position is less than the specified one";
    }
}
