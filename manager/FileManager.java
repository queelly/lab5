package manager;

import models.Worker;
import utility.ArrayStringFromWorkerFormatter;
import utility.WorkerFromArrayStringFormatter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager {

    private String fileName;
    private PrinterManager printerManager = new PrinterManager();

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    private String escape(String value) {
        if (value == null) return "";
        return value.replace("\"", "\"\"").replace(";", "\";\"");
    }

    public int writeCollectionToCSV(CollectionManager collectionManager) {
        try (FileWriter writer = new FileWriter(fileName, StandardCharsets.UTF_8)) {
            for (Worker worker : collectionManager.getCollection()) {
                writer.write(Arrays.stream(
                        ArrayStringFromWorkerFormatter.ArrayStringFromWorker(worker)
                ).map(this::escape).collect(Collectors.joining(";")) + "\n");

            }
        } catch (IOException e) {
            printerManager.printErr("can't write to file: " + e.getMessage());
            return -1;
        }
        printerManager.println("Collection was successfully saved!");
        return 0;
    }

    public ArrayList<Worker> loadCollectionFromCSV() {
        ArrayList<Worker> collection = new ArrayList<>();
        try (
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))
        ) {
            StringBuilder curField = new StringBuilder();
            List<String> curRow = new ArrayList<>();
            boolean inQuotes = false;
            int c;
            while ((c = bis.read()) != -1) {
                char ch = (char) c;
                if (inQuotes) {
                    if (ch == '"') {
                        bis.mark(1);
                        int nextC = bis.read();
                        if (nextC == '"') {
                            curField.append('"');
                        } else {
                            inQuotes = false;
                            if (nextC != -1) {
                                bis.reset();
                            }
                        }
                    } else {
                        curField.append(ch);
                    }
                } else {
                    if (ch == '\"') {
                        inQuotes = true;
                    } else if (ch == ';') {
                        curRow.add(curField.toString());
                        curField = new StringBuilder();
                    } else if (ch == '\n') {
                        curRow.add(curField.toString());
                        curField = new StringBuilder();
                        Worker workerToAdd = WorkerFromArrayStringFormatter.workerFromArrayString(
                                curRow.toArray(new String[] {})
                        );
                        if (workerToAdd != null && workerToAdd.isValid()) {
                            collection.add(workerToAdd);
                        }
                        curRow.clear();
                    } else {
                        curField.append(ch);
                    }
                }
            }
        } catch (IOException e) {
            printerManager.printErr("can't read file: " + e.getMessage());
            return collection;
        }
        return collection;
    }
}
