package ru.job4j.tracker.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;
import ru.job4j.tracker.store.MemTracker;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByNameTest {

    private Input input;

    @BeforeEach
    public void init() {
        input = mock(Input.class);
    }

    @Test
    public void whenFindByNameSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("test1"));
        FindByName findByNameAction = new FindByName(output);

        when(input.askStr(any(String.class))).thenReturn("test1");

        findByNameAction.execute(input, tracker);
        String result = output.toString();
        assertThat(result).contains("=== Вывод заявок по имени ===");
        assertThat(result).contains("test1");
    }

    @Test
    public void whenNotFindByName() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("test1"));
        FindByName findByNameAction = new FindByName(output);

        when(input.askStr(any(String.class))).thenReturn("test2");

        findByNameAction.execute(input, tracker);
        String result = output.toString();
        assertThat(result).contains("=== Вывод заявок по имени ===");
        assertThat(result).contains("не найдены");
    }
}
