package ru.job4j.ex;

public class FactorialRecursion {
    public static int calc(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * calc(n - 1);
    }

    public static void main(String[] args) {
        int result = calc(3);
        System.out.println(result);
    }
}
