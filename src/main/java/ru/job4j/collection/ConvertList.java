package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;

public class ConvertList {
    public static List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] current : list) {
            for (int i : current) {
                result.add(i);
            }
        }
        return result;
    }
}
