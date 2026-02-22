package models.builders;

import manager.AskManager;
import manager.ParserManager;
import manager.PrinterManager;
import manager.ScannerManager;
import models.Organization;

public class OrganizationBuilder extends Builder<Organization>{

    private ScannerManager scannerManager;
    private final PrinterManager printerManager = new PrinterManager();
    private AskManager askManager;
    private boolean working = true;

    public OrganizationBuilder(ScannerManager scannerManager) {
        this.scannerManager = scannerManager;
    }

    public void stopWorking() {
        working = false;
        if (askManager != null) {
            askManager.stopWorking();
        }
    }

    public Organization build() {
        askManager = new AskManager(scannerManager);
        while (working) {
            printerManager.println("Enter some information about Organization:");
            Organization organization = new Organization(
                askManager.askArgument(
                    "Annual turnover",
                    "(greater than 0 or empty)",
                    0.0,
                    Double.MAX_VALUE - 1,
                    true,
                    ParserManager.parseDouble
                ),
                askManager.askArgument(
                    "Employees count",
                    "(greater than 0 or empty)",
                    0,
                    Integer.MAX_VALUE - 1,
                    true,
                    ParserManager.parseInteger
                )
            );
            if (organization.isValid()) {
                return organization;
            } else {
                printerManager.printErr("Invalid Organization information! Try again. Please, follow restrictions while printing!");
            }
        }
        printerManager.println("Building was stopped cause of shutting down the Program!");
        return null;
    }
}
