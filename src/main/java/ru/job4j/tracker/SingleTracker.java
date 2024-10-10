package ru.job4j.tracker;

import java.util.List;

public final class SingleTracker {

    private static SingleTracker singleTracker;

    private final Store store = new MemTracker();

    private SingleTracker() {
    }

    public static SingleTracker getSingleTracker() {
        if (singleTracker == null) {
            return new SingleTracker();
        }
        return singleTracker;
    }

    public Item add(Item item) {
        return store.add(item);
    }

    public Item findById(int id) {
        return store.findById(id);
    }

    public List<Item> findAll() {
        return store.findAll();
    }

    public List<Item> findByName(String key) {
        return store.findByName(key);
    }

    public boolean replace(int id, Item item) {
        return store.replace(id, item);
    }

    public boolean delete(int id) {
        store.delete(id);
        return store.findById(id) == null;
    }
}
