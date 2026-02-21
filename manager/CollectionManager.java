package manager;

import models.Position;
import models.Worker;

import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager {

    private LocalDateTime initializationDateTime = LocalDateTime.now();
    private final ArrayDeque<Worker> collection = new ArrayDeque<>();

    public LocalDateTime getInitializationDateTime() {
        return initializationDateTime;
    }

    public boolean add(Worker worker) {
        if (worker != null && worker.isValid()) {
            collection.add(worker);
            return true;
        }
        return false;
    }

    public Worker findById(Long id) {
        for (Worker worker : collection) {
            if (worker.getId().equals(id)) {
                return worker;
            }
        }
        return null;
    }

    public void updateId(Long id, Worker worker) {
        Worker currentWorker = findById(id);
        if (currentWorker != null && worker.isValid()) {
            collection.remove(currentWorker);
            worker.setId(currentWorker.getId());
            collection.add(worker);
        }
    }

    public boolean removeById(Long id) {
        Worker worker = findById(id);
        if (worker != null) {
            IdManager.removeFromUsedIds(worker.getId());
            return collection.remove(worker);
        }
        return false;
    }

    public boolean clear() {
        initializationDateTime = LocalDateTime.now();
        collection.clear();
        return true;
    }

    public Worker removeHead() {
        if (!collection.isEmpty()) {
            Worker worker = collection.removeFirst();
            IdManager.removeFromUsedIds(worker.getId());
            return worker;
        }
        return null;
    }

    public boolean addIfMax(Worker worker) {
        if (!worker.isValid()) {
            return false;
        }
        if (collection.isEmpty()) {
            return add(worker);
        }
        Worker maxWorker = Collections.max(collection);
        return worker.compareTo(maxWorker) > 0 && add(worker);
    }

    public boolean addIfMin(Worker worker) {
        if (!worker.isValid()) {
            return false;
        }
        if (collection.isEmpty()) {
            return add(worker);
        }
        Worker minWorker = Collections.min(collection);
        return worker.compareTo(minWorker) < 0 && add(worker);
    }

    public List<Worker> filterStartsWithName(String name) {
        return collection.stream().filter(object -> object.getName().startsWith(name)).toList();
    }

    public List<Worker> filterLessThanPosition(Position position) {
        return collection.stream().filter(object -> object.getName() != null && object.getPosition().compareTo(position) < 0).toList();
    }

    public List<Double> getSalariesAscending() {
        return collection.stream().map(Worker::getSalary).sorted().toList();
    }

    public ArrayDeque<Worker> getCollection() {
        return collection;
    }

    public String getCollectionAsString() {
        StringBuilder collectionString = new StringBuilder();
        if (!collection.isEmpty()) {
            for (Worker worker : collection) {
                collectionString.append(worker);
                collectionString.append("\n");
            }
        } else {
            collectionString = new StringBuilder("Collection is currently empty!\n");
        }
        return collectionString.toString();
    }

    public long getCollectionSize() {
        return collection.size();
    }

    public String getCollectionType() {
        return collection.getClass().getTypeName();
    }
}
