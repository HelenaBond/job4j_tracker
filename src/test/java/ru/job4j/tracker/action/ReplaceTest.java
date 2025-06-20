package ru.job4j.tracker.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;
import ru.job4j.tracker.store.MemTracker;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReplaceTest {

    private Input input;

    @BeforeEach
    public void init() {
       input = mock(Input.class);
    }

    @Test
    public void whenItemWasReplacedSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Replace replaceAction = new Replace(output);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        replaceAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Редактирование заявки ===" + ln
                        + "Заявка изменена успешно" + ln
        );
    }

    @Test
    public void whenItemWasNotReplaced() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        Replace replaceAction = new Replace(output);

        replaceAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Редактирование заявки ===" + ln
                        + "Ошибка замены заявки" + ln
        );
    }
}
