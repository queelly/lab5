package utility;

import models.Worker;

import java.time.format.DateTimeFormatter;

public class ArrayStringFromWorkerFormatter {

    public static String[] ArrayStringFromWorker(Worker worker) {
        return new String[] {
            worker.getId() != null ? worker.getId().toString() : "null",
            worker.getName() != null ? worker.getName() : "null",
            worker.getCoordinates() != null ? worker.getCoordinates().toString() : "null",
            worker.getCreationDate() != null ?
                worker.getCreationDate().format(DateTimeFormatter.ISO_DATE_TIME) : "null",
            worker.getSalary() != null ? worker.getSalary().toString() : "null",
            worker.getPosition() != null ? worker.getPosition().toString() : "null",
            worker.getStatus() != null ? worker.getStatus().toString() : "null",
            worker.getOrganization() != null ? worker.getOrganization().toString() : "null"
        };
    }
}
