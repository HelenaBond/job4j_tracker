package ru.job4j.cast;

public class Bus implements Vehicle {
    @Override
    public void move() {
        System.out.println("Едем по дороге.");
    }

    @Override
    public void transportationPoint() {
        System.out.println("Место сбора - конечная остановка.");
    }
}
