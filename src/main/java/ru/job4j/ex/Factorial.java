package ru.job4j.ex;

public class Factorial {
    public int calc(int number) {
        int result = 1;
        if (validNum(number)) {
            for (int index = 2; index <= number; index++) {
                result *= index;
            }
        }
        return result;
    }

    public boolean validNum(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number could not be less than 0");
        }
        return true;
    }

    public static void main(String[] args) {
        new Factorial().calc(-1);
    }
}
