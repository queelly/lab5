package utility;

import models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class WorkerFromArrayStringFormatter {

    public static Worker workerFromArrayString(String[] arr) {
        if (arr.length != 8) {
            return null;
        }
        Long id = null;
        String name = null;
        Coordinates coordinates = null;
        LocalDateTime creationDate;
        Double salary = null;
        Position position = null;
        Status status = null;
        Organization organization = null;
        try {
            id = Long.parseLong(arr[0]);
        } catch (NumberFormatException e) {
            id = null;
        }
        name = arr[1].trim();
        float x = 0;
        Double y = null;
        try {
            String xString = arr[2].trim().split(";")[0];
            if (xString.startsWith("(X=")) {
                x = Float.parseFloat(xString.substring(3));
            }
        } catch (RuntimeException e) {
        }
        try {
            String yString = arr[2].trim().split(";")[1];
            if (yString.startsWith("Y=") && yString.endsWith(")")) {
                y = Double.parseDouble(yString.substring(2, yString.length() - 1));
            }
        } catch (RuntimeException e) {
        }
        coordinates = new Coordinates(x, y);
        try {
            creationDate = LocalDateTime.parse(arr[3].trim(), DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            creationDate = null;
        }
        try {
            salary = Double.parseDouble(arr[4].trim());
        } catch (NumberFormatException e) {
        }
        String positionString = arr[5].trim();
        for (Position value : Position.values()) {
            if (value.toString().equalsIgnoreCase(positionString)) {
                position = value;
            }
        }
        if (positionString.isEmpty() || positionString.equalsIgnoreCase("null")) {
            position = null;
        }
        String statusString = arr[6].trim();
        for (Status value : Status.values()) {
            if (value.toString().equalsIgnoreCase(statusString)) {
                status = value;
            }
        }
        if (statusString.isEmpty() || statusString.equalsIgnoreCase("null")) {
            status = null;
        }
        String organizationString = arr[7].trim();
        if (organizationString.startsWith("Organization{") && organizationString.endsWith("}") && organizationString.contains(";")) {
            try {
                organizationString = organizationString.substring(13, organizationString.length() - 1);
                Double annualTurnover = 0.;
                String annualTurnoverString = organizationString.split(";")[0];
                if (annualTurnoverString.startsWith("annualTurnover=")) {
                    try {
                        annualTurnover = Double.parseDouble(annualTurnoverString.substring(15));
                    } catch (NumberFormatException e) {
                        annualTurnover = null;
                    }
                }
                Integer employeesCount = 0;
                String employeesCountString = organizationString.split(";")[1];
                if (employeesCountString.startsWith("employeesCount=")) {
                    try {
                        employeesCount = Integer.parseInt(employeesCountString.substring(15));
                    } catch (NumberFormatException e) {
                        employeesCount = null;
                    }
                }
                organization = new Organization(annualTurnover, employeesCount);
            } catch (RuntimeException e) {
            }
        }
        return new Worker(id, name, coordinates, creationDate, salary, position, status, organization);
    }
}
