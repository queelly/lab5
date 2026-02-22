package models.builders;

import manager.*;
import models.Position;
import models.Status;
import models.Worker;
import utility.EnumNames;

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
                askManager.askArgument(
                    "Name",
                    "(not empty)",
                    null,
                    null,
                    false,
                    ParserManager.parseString
                ),
                new CoordinatesBuilder(scannerManager).build(),
                LocalDateTime.now(),
                askManager.askArgument(
                    "Salary",
                    "(greater than 0 or empty)",
                    0.0,
                    Double.MAX_VALUE - 1,
                    true,
                    ParserManager.parseDouble
                ),
                askManager.askArgument(
                    "Position",
                    "(enter one of possible values: " +
                        EnumNames.names(Position.class) +
                        ", you may write upper either lower case letters (may be empty))",
                    null,
                    null,
                    true,
                    ParserManager.parseEnum(Position.class)
                ),
                askManager.askArgument(
                    "Status",
                    "(enter one of possible values: " +
                        EnumNames.names(Status.class) +
                        ", you may write upper either lower case letters (not empty))",
                    null,
                    null,
                    false,
                    ParserManager.parseEnum(Status.class)
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
