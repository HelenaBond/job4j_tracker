package ru.job4j.collection;
import java.util.*;

public class ATM {

    public static TreeMap<Integer, Integer> iWantToGet(int amountRequired, Map<Integer, Integer> limits) {
        List<Integer> nominals = new ArrayList<>(limits.keySet());
        nominals.sort(Collections.reverseOrder());

        return collect(amountRequired, nominals, limits);
    }

    private static TreeMap<Integer, Integer> collect(int amount, List<Integer> nominals, Map<Integer, Integer> limits) {
        if (amount == 0) {
            return new TreeMap<>(Comparator.reverseOrder());
        }

        if (nominals.isEmpty()) {
            return null;
        }

        int currentNominal = nominals.get(0);
        int availableNotes = limits.getOrDefault(currentNominal, 0);
        int notesNeeded = amount / currentNominal;
        int numberOfNotes = Math.min(availableNotes, notesNeeded);

        for (int i = numberOfNotes; i >= 0; i--) {
            TreeMap<Integer, Integer> result = collect(amount - i * currentNominal, nominals.subList(1, nominals.size()), limits);

            if (result != null) {
                if (i > 0) {
                    result.put(currentNominal, i);
                }
                return result;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> limits = new HashMap<>();
        limits.put(500, 100);
        limits.put(200, 2);
        limits.put(100, 3);

        System.out.println(iWantToGet(230, limits));
        System.out.println(iWantToGet(1000, limits));
        System.out.println(iWantToGet(200, limits));
        System.out.println(iWantToGet(400, limits));
        System.out.println(iWantToGet(120, limits));
        System.out.println(iWantToGet(275, limits));
    }
}






