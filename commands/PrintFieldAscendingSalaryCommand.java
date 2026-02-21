package commands;

import manager.CollectionManager;
import manager.PrinterManager;

import java.util.List;

public class PrintFieldAscendingSalaryCommand implements Executable {

    private PrinterManager printerManager = new PrinterManager();
    private CollectionManager collection;

    public PrintFieldAscendingSalaryCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public int execute(String... args) {
        if (args.length != 0) {
            printerManager.printErr("Command does not accept args!");
            return 1;
        }
        List<Double> sortedSalaries = collection.getSalariesAscending();
        if (sortedSalaries.isEmpty()) {
            printerManager.println("Result is empty!");
        } else {
            printerManager.println("Sorted result:");
            for (Double salary : sortedSalaries) {
                printerManager.println(salary);
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return ": print Salaries of Workers in ascending order";
    }
}
