package ru.job4j.tracker.oop.colobok;

public class Ball {
    public void tryRun(boolean condition) {
        if (condition) {
            System.out.println("Колобок съеден.");
        } else {
            System.out.println("Колобок сбежал.");
        }
    }
}
