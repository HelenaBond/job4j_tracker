package ru.job4j.tracker;

public final class SingleTracker {

    private static SingleTracker singleTracker;

    private final Tracker tracker = new Tracker();

    private SingleTracker() {
    }

    public static SingleTracker getSingleTracker() {
        if (singleTracker == null) {
            return new SingleTracker();
        }
        return singleTracker;
    }

    public Item add(Item item) {
        return tracker.add(item);
    }

    public Item findById(int id) {
        return tracker.findById(id);
    }

    public Item[] findAll() {
        return tracker.findAll();
    }

    public Item[] findByName(String key) {
        return tracker.findByName(key);
    }

    public boolean replace(int id, Item item) {
        return tracker.replace(id, item);
    }

    public void delete(int id) {
        tracker.delete(id);
    }
}