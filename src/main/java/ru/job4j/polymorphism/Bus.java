package ru.job4j.polymorphism;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("The bus is moving.");
    }

    @Override
    public void passengers(int amount) {
        System.out.println(amount + " passengers are now on the bus.");
    }

    @Override
    public int fillUp(int liter) {
        int dieselLiterPrice = 57;
        return liter * dieselLiterPrice;
    }
}
