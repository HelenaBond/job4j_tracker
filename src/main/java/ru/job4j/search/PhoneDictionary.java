package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private final ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список пользователей, которые прошли проверку.
     */
    public ArrayList<Person> find(String key) {
        Predicate<Person> ascByName = p -> p.getName().contains(key);
        Predicate<Person> ascBySurname = p -> p.getSurname().contains(key);
        Predicate<Person> ascByAddress = p -> p.getAddress().contains(key);
        Predicate<Person> ascByPhone = p -> p.getPhone().contains(key);
        Predicate<Person> combine = ascByName.or(ascBySurname).or(ascByAddress).or(ascByPhone);
        ArrayList<Person> result = new ArrayList<>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
