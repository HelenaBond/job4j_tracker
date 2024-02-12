package ru.job4j.cast;

public class Route {
    public static void main(String[] args) {
        Vehicle point1 = new Bus();
        Vehicle point2 = new Train();
        Vehicle point3 = new Airplane();
        Vehicle point4 = new Bus();
        Vehicle point5 = new Airplane();
        Vehicle point6 = new Train();
        Vehicle point7 = new Bus();

        Vehicle[] moscowKazakhstanItaly = {point1, point2, point3, point4, point5, point6, point7};
        for (Vehicle transport : moscowKazakhstanItaly) {
            transport.transportationPoint();
            transport.move();
        }
    }
}
