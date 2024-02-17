package ru.job4j.cast;

public class Airplane implements Vehicle {
    @Override
    public void move() {
        System.out.println("Летим.");
    }

    @Override
    public void transportationPoint() {
        System.out.println("Место сбора - аэропорт.");
    }
}
