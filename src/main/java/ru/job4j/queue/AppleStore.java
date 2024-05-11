package ru.job4j.queue;

import java.util.Queue;

public class AppleStore {
    private final Queue<Customer> queue;

    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public String getLastHappyCustomer() {
        Queue<Customer> currentQueue = queue;
        for (int i = 1; i < count; i++) {
            currentQueue.poll();
        }
        return currentQueue.poll().name();
    }

    public String getFirstUpsetCustomer() {
        Queue<Customer> currentQueue = queue;
        for (int i = 0; i < count; i++) {
            currentQueue.poll();
        }
        return currentQueue.poll().name();
    }
}
