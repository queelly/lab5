package manager;

import java.util.Scanner;

public class ScannerManager {

    private final Scanner scanner;

    public String readLine() {
        return scanner.nextLine();
    }

    public ScannerManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public ScannerManager() {
        this(new Scanner(System.in));
    }
}
