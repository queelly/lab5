package models.builders;

import manager.AskManager;
import manager.IdManager;
import manager.PrinterManager;
import manager.ScannerManager;
import models.Position;
import models.Status;
import models.Worker;

import java.time.LocalDateTime;

public class WorkerBuilder extends Builder<Worker> {

    private ScannerManager scannerManager;
    private PrinterManager printerManager = new PrinterManager();

    public WorkerBuilder(ScannerManager scannerManager) {
        this.scannerManager = scannerManager;
    }

    @Override
    public Worker build() {
        AskManager askManager = new AskManager(scannerManager);
        while (true) {
            printerManager.println("Enter some information about Worker:");
            Worker worker = new Worker(
                IdManager.generateId(),
                askManager.askString(
                    "Name",
                    "(not empty)"
                ),
                new CoordinatesBuilder(scannerManager).build(),
                LocalDateTime.now(),
                askManager.askDouble(
                    "Salary",
                    "(greater than 0 or empty)"
                ),
                askManager.askEnum(
                    Position.class,
                    "(may be empty)"
                ),
                askManager.askEnum(
                    Status.class,
                    "(not empty)"
                ),
                new OrganizationBuilder(scannerManager).build()
            );
            if (worker.isValid()) {
                return worker;
            } else {
                printerManager.printErr("Invalid Worker information! Try again. Please, follow restrictions while printing!");
            }
        }

    }
}
