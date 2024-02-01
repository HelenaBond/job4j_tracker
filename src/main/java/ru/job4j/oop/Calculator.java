package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int y) {
        return y - x;
    }

    public int divide(int num) {
        return num / x;
    }

    public int sumAllOperation(int num) {
        return sum(num) + multiply(num) + minus(num) + divide(num);
    }

    public static void main(String[] args) {
        int plusResult = Calculator.sum(10);
        int minusResult = Calculator.minus(8);
        Calculator calculator = new Calculator();
        int multiResult = calculator.multiply(5);
        int divideResult = calculator.divide(10);
        int sumAllOperationRes = calculator.sumAllOperation(8);
        System.out.println(plusResult);
        System.out.println(multiResult);
        System.out.println(minusResult);
        System.out.println(divideResult);
        System.out.println(sumAllOperationRes);
    }
}
