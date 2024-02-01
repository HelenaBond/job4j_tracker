package ru.job4j.checkstyle;

public class Broken {
    public static final String VALUE = "";

    private int emptySize = 10;
    private String surname;
    private String name;

    public Broken() {

    }

    public void echo() { }

    public void media(Object obj) {
        if (obj != null) {
            System.out.println(obj);
        }

    }

    public void method(int a, int b, int c) {

    }
}
