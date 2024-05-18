package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemDescByNameTest {

    @Test
    public void whenSortByNameAsc() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("First"));
        items.add(new Item("Third"));
        items.add(new Item("Second"));
        Collections.sort(items, new ItemDescByName());
        List<Item> expected = new ArrayList<>(List.of(
                new Item("Third"),
                new Item("Second"),
                new Item("First")
        ));
        assertThat(items).isEqualTo(expected);
    }
}