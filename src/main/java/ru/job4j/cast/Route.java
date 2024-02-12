package ru.job4j.cast;

public class Route {
    public static void main(String[] args) {
        Vehicle start = new Bus();
        Vehicle toMoscow = new Train();
        Vehicle toAlmaty = new Airplane();
        Vehicle transfer = new Bus();
        Vehicle toRome = new Airplane();
        Vehicle excursion = new Train();
        Vehicle end = new Bus();

        Vehicle[] weekend = {start, toMoscow, toAlmaty, transfer, toRome, excursion, end};
        for (Vehicle transport : weekend) {
            transport.transportationPoint();
            transport.move();
        }
    }
}
