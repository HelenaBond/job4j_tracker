package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int first = right.split("/")[0].compareTo(left.split("/")[0]);
        if (first == 0) {
            first = left.compareTo(right);
        }
        return first;
    }
}
