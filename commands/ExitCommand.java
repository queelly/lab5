package commands;

import manager.PrinterManager;
import manager.RuntimeManager;
import manager.StackManager;

public class ExitCommand implements Executable {

    private PrinterManager printerManager = new PrinterManager();

    @Override
    public int execute(String... args) {
        if (args.length != 0) {
            printerManager.printErr("Command does not accept args!");
            return 1;
        }
        printerManager.println("Shutting down the Program!");
        RuntimeManager.setSaveBeforeExit(false);
        StackManager.clearCommandSet();
        System.exit(0);
        return 0;
    }

    @Override
    public String toString() {
        return ": shut down the Program (without saving the file)";
    }
}
