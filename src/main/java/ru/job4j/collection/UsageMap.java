package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> data = new HashMap<>();
        data.put("lena@mail.com", "Бондарева Елена Викторовна");
        data.put("dasha@mail.com", "Осечкина Дарья Ивановна");
        data.put("katya@mail.com", "Власова Екатерина Леонидовна");
        for (Map.Entry<String, String> user : data.entrySet()) {
            System.out.println(user);
        }
    }
}
