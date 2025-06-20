package ru.job4j.tracker.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;
import ru.job4j.tracker.store.MemTracker;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteTest {

    private Input input;

    @BeforeEach
    public void init() {
        input = mock(Input.class);
    }

    @Test
    void whenDeleteItemSuccessfully() {
        MemTracker tracker = new MemTracker();
        Output output = new StubOutput();
        String itemName = "Deleted item";
        tracker.add(new Item(itemName));
        Delete deleteAction = new Delete(output);

        when(input.askInt(any(String.class))).thenReturn(1);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Удаление заявки ===" + ln
                        + "Заявка удалена успешно" + ln
        );
    }

    @Test
    void whenItemNotDeleted() {
        MemTracker tracker = new MemTracker();
        Output output = new StubOutput();
        Delete deleteAction = new Delete(output);

        when(input.askInt(any(String.class))).thenReturn(1);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Удаление заявки ===" + ln
                        + "Ошибка удаления заявки" + ln
        );
    }
}
