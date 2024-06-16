package ru.job4j.collection;
import java.util.*;

public class ATM {

    //amount < общей суммы денег в банкомате провалидировано заранее.
    public static Map<Integer, Integer> giveMyMoney(int amount, TreeMap<Integer, Integer> availableNotes) {
        int[] memo = new int[amount + 1];
        memo[0] = -1;
        for (Map.Entry<Integer, Integer> entry : availableNotes.entrySet()) {
            int banknote = entry.getKey();
            int count = entry.getValue();
            for (int i = 1; i < memo.length; i++) {
                if (i >=  banknote && memo[i - banknote] != 0 && count > 0) {
                    memo[i] = Math.max(memo[i], banknote);
                    if (memo[memo.length - 1] != 0) {
                        return getCash(memo);
                    }
                    count--;
                }
            }
        }
        return null;
    }

    private static Map<Integer, Integer> getCash(int[] memo) {
        Map<Integer, Integer> res = new TreeMap<>();
        int index = memo.length - 1;
        while (index > 0) {
            res.put(memo[index], res.getOrDefault(memo[index], 0) + 1);
            index -= memo[index];
        }
        return res;
    }

    public static void main(String[] args) {
        int amount = 1000;

        // Начальное состояние купюр в банкомате
        TreeMap<Integer, Integer> availableNotes = new TreeMap<>(Comparator.reverseOrder());
        availableNotes.put(500, 2);
        availableNotes.put(200, 200);

        // Выдаем сумму amount из банкомата
        Map<Integer, Integer> cash = giveMyMoney(amount, availableNotes);
        if (cash != null) {
            System.out.println("Выдано:");
            for (Map.Entry<Integer, Integer> entry : cash.entrySet()) {
                System.out.println(entry.getKey() + " рублей: " + entry.getValue() + " купюр");
            }
        } else {
            System.out.println("Невозможно выдать требуемую сумму");
        }
    }
}





