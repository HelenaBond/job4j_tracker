package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    public Item[] findAll() {
        return Arrays.copyOf(items, size);
    }

    public Item[] findByName(String key) {
        Item[] result = new Item[size];
        int len = 0;
        for (int i = 0; i < size; i++) {
            if (key.equals(items[i].getName())) {
                result[len] = items[i];
                len++;
            }
        }
        return Arrays.copyOf(result, len);
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            item.setId(id);
            items[index] = item;
        }
        return result;
    }

    public void delete(int id) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
            items[size - 1] = null;
            size--;

        }
    }

    private int indexOf(int id) {
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                return index;
            }
        }
        return -1;
    }
}
