package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private final LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определяется по полю приоритет.
     * Для вставки использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        var index = 0;
        var priority = task.getPriority();
        for (var element : tasks) {
            if (element.getPriority() > priority) {
                break;
            }
                index++;
        }
        this.tasks.add(index, task);
    }

    public Task take() {
        return tasks.poll();
    }
}
