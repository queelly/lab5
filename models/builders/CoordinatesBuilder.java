package models.builders;

import manager.AskManager;
import manager.PrinterManager;
import manager.ScannerManager;
import models.Coordinates;

public class CoordinatesBuilder extends Builder<Coordinates> {

    private ScannerManager scannerManager;
    private final PrinterManager printerManager = new PrinterManager();
    private Float x;
    private Double y;

    public CoordinatesBuilder(ScannerManager scannerManager) {
        this.scannerManager = scannerManager;
    }

    @Override
    public Coordinates build() {
        AskManager askManager = new AskManager(scannerManager);
        while (true) {
            printerManager.println("Enter the Coordinates:");
            x = askManager.askFloat(
                "X coordinate",
                "(maximal value is 592, not empty)",
                    -Float.MIN_VALUE,
                    592.0F,
                    false
            );
            y = askManager.askDouble(
                "Y coordinate",
                "(maximal value is 846, not empty)",
                    -Double.MIN_VALUE,
                    846.0,
                    false
            );
            Coordinates coordinates = new Coordinates(x != null ? x : 0, y);
            if (coordinates.isValid()) {
                return coordinates;
            } else {
                printerManager.printErr("Invalid Coordinates information! Try again. Please, follow restrictions while printing!");
            }
        }
    }
}
