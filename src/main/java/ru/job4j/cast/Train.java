package ru.job4j.cast;

public class Train implements Vehicle {
    @Override
    public void move() {
        System.out.println("Едем по рельсам.");
    }

    @Override
    public void transportationPoint() {
        System.out.println("Место сбора - вокзал.");
    }
}
