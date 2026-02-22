package models.builders;

import manager.AskManager;
import manager.ParserManager;
import manager.PrinterManager;
import manager.ScannerManager;
import models.Organization;

public class OrganizationBuilder extends Builder<Organization>{

    private ScannerManager scannerManager;
    private final PrinterManager printerManager = new PrinterManager();

    public OrganizationBuilder(ScannerManager scannerManager) {
        this.scannerManager = scannerManager;
    }

    public Organization build() {
        AskManager askManager = new AskManager(scannerManager);
        while (true) {
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
    }
}
