package ru.job4j.tracker.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;
import ru.job4j.tracker.store.MemTracker;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIdTest {

    private Input input;

    @BeforeEach
    public void init() {
        input = mock(Input.class);
    }

    @Test
    public void whenFindByIdSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("test1"));
        FindById findByIdAction = new FindById(output);

        when(input.askInt(any(String.class))).thenReturn(1);

        findByIdAction.execute(input, tracker);
        String result = output.toString();
        assertThat(result).contains("=== Вывод заявки по id ===");
        assertThat(result).contains("test1");
    }

    @Test
    public void whenNotFindById() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("test1"));
        FindById findByIdAction = new FindById(output);

        when(input.askInt(any(String.class))).thenReturn(2);

        findByIdAction.execute(input, tracker);
        String result = output.toString();
        assertThat(result).contains("=== Вывод заявки по id ===");
        assertThat(result).contains("не найдена");
    }
}
