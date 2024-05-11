package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int leftNumber = Integer.parseInt(left.substring(0, left.indexOf(".")).trim());
        int rightNumber = Integer.parseInt(right.substring(0, right.indexOf(".")).trim());
        return Integer.compare(leftNumber, rightNumber);
    }
}
