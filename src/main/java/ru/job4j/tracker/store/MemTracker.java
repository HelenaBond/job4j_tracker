package ru.job4j.tracker.store;

import ru.job4j.tracker.Item;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MemTracker implements Store {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    public List<Item> findAll() {
        return List.copyOf(items);
    }

    public List<Item> findByName(String key) {
        List<Item> result = new LinkedList<>();
        for (Item current : items) {
            if (key.equals(current.getName())) {
                result.add(current);
            }
        }
        return result;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            item.setId(id);
            items.set(index, item);
        }
        return result;
    }

    public void delete(int id) {
        int index = indexOf(id);
            items.remove(index);
    }

    private int indexOf(int id) {
        for (int index = 0; index < items.size(); index++) {
            Item item = items.get(index);
            if (item.getId() == id) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public void close() {
    }
}
