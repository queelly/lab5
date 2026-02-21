package manager;

import models.Coordinates;
import utility.EnumNames;

import java.math.BigInteger;

/**
 * Class for querying different types of data.
 *
 */
public class AskManager {

    private ScannerManager scannerManager;
    private PrinterManager printerManager = new PrinterManager();

    /**
     * Class constructor
     *
     * @param scannerManager object of scanner manager.
     */
    public AskManager(ScannerManager scannerManager) {
        this.scannerManager = scannerManager;
    }

    /**
     * String asking method
     *
     * @param fieldName required field name
     * @param restrictions restrictions to print
     * @return String object that is result of asking (null if input is empty).
     */
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

    /**
     * Any Enum class asking method
     *
     * @param enumClass instance of Enum class that is asking
     * @param restrictions restrictions to print
     * @return one of Enum constants if input is correct and null if empty
     * @param <T> Enum class
     */
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

    /**
     * Long asking method
     *
     * @param fieldName required field name
     * @param restrictions restrictions to print
     * @return Long object that is result of asking (null if input is empty).
     */
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

    /**
     * Integer asking method
     *
     * @param fieldName required field name
     * @param restrictions restrictions to print
     * @return Integer object that is result of asking (null if input is empty).
     */
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

    /**
     * Double asking method
     *
     * @param fieldName required field name
     * @param restrictions restrictions to print
     * @return Double object that is result of asking (null if input is empty).
     */
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

    /**
     * Float asking method
     *
     * @param fieldName required field name
     * @param restrictions restrictions to print
     * @return Float object that is result of asking (null if input is empty).
     */
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
