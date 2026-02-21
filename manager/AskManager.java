package manager;

import models.Coordinates;
import utility.EnumNames;

import java.math.BigInteger;

public class AskManager {

    private ScannerManager scannerManager;
    private PrinterManager printerManager = new PrinterManager();

    public AskManager(ScannerManager scannerManager) {
        this.scannerManager = scannerManager;
    }

    public String askString(String fieldName, String restrictions) {
        printerManager.println("Enter " + fieldName + " " + restrictions + ": ");
        printerManager.print(">>> ");
        String inputLine = scannerManager.readLine().trim();
        if (!inputLine.isEmpty()) {
            return inputLine;
        } else {
            return null;
        }
    }

    public <T extends Enum<T>> T askEnum (Class<T> enumClass, String restrictions) {
        while (true) {
            printerManager.println("Enter one of possible values: " + EnumNames.names(enumClass) + ", you may write upper either lower case letters " + restrictions + ":");
            printerManager.print(">>> ");
            String inputLine = scannerManager.readLine().trim();
            for (T value : enumClass.getEnumConstants()) {
                if (value.toString().equalsIgnoreCase(inputLine)) {
                    return value;
                }
            }
            if (inputLine.isEmpty()) {
                return null;
            }
            printerManager.printErr("Wrong input format! Please, try again.");
        }
    }

    public Long askLong(String fieldName, String restrictions) {
        while (true) {
            printerManager.println("Enter " + fieldName + " " + restrictions + ":");
            printerManager.print(">>> ");
            String inputLine = scannerManager.readLine().trim();
            try {
                BigInteger bigInteger = new BigInteger(inputLine);
                if (bigInteger.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
                    printerManager.printErr("Your value is too large so it was set to " + Long.MAX_VALUE);
                    return Long.MAX_VALUE;
                }
                if (bigInteger.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0) {
                    printerManager.printErr("Your value is too small so it was set to " + Long.MIN_VALUE);
                    return Long.MIN_VALUE;
                }
                return bigInteger.longValue();
            } catch (NumberFormatException e) {
                if (inputLine.isEmpty()) {
                    return null;
                }
                printerManager.printErr("Wrong input format! Please, try again.");
            }
        }
    }

    public Integer askInteger(String fieldName, String restrictions) {
        while (true) {
            printerManager.println("Enter " + fieldName + " " + restrictions + ":");
            printerManager.print(">>> ");
            String inputLine = scannerManager.readLine().trim();
            try {
                BigInteger bigInteger = new BigInteger(inputLine);
                if (bigInteger.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
                    printerManager.printErr("Your value is too large so it was set to " + Integer.MAX_VALUE);
                    return Integer.MAX_VALUE;
                }
                if (bigInteger.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
                    printerManager.printErr("Your value is too small so it was set to " + Integer.MIN_VALUE);
                    return Integer.MIN_VALUE;
                }
                return bigInteger.intValue();
            } catch (NumberFormatException e) {
                if (inputLine.isEmpty()) {
                    return null;
                }
                printerManager.printErr("Wrong input format! Please, try again.");
            }
        }
    }

    public Double askDouble(String fieldName, String restrictions) {
        while (true) {
            printerManager.println("Enter " + fieldName + " " + restrictions + ":");
            printerManager.print(">>> ");
            String inputLine = scannerManager.readLine().trim();
            try {
                Double parsedDouble = Double.parseDouble(inputLine);
                if (!inputLine.isEmpty()) {
                    return parsedDouble;
                } else {
                    return null;
                }
            } catch (NumberFormatException e) {
                if (inputLine.isEmpty()) {
                    return null;
                }
                printerManager.printErr("Wrong input format! Please, try again.");
            }
        }
    }

    public Float askFloat(String fieldName, String restrictions) {
        while (true) {
            printerManager.println("Enter " + fieldName + " " + restrictions + ":");
            printerManager.print(">>> ");
            String inputLine = scannerManager.readLine().trim();
            try {
                Float parsedFloat = Float.parseFloat(inputLine);
                if (!inputLine.isEmpty()) {
                    return parsedFloat;
                } else {
                    return null;
                }
            } catch (NumberFormatException e) {
                if (inputLine.isEmpty()) {
                    return null;
                }
                printerManager.printErr("Wrong input format! Please, try again.");
            }
        }
    }
}
