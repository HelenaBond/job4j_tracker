package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StartUITest {
    @Test
    void whenCreateItem() {
        Input input = new MockInput(
                new String[] {"0", "Item name", "1"}
        );
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        List<UserAction> actions = Arrays.asList(
                new Create(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName()).isEqualTo("Item name");
    }

    @Test
    void whenDeleteItem() {
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        Item item = memTracker.add(new Item("Deleted item"));
        Input input = new MockInput(
                new String[] {"0", Integer.toString(item.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new Delete(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        assertThat(memTracker.findById(item.getId())).isNull();
    }

    @Test
    void whenReplaceItem() {
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        Item item = memTracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input input = new MockInput(
                new String[] {"0", Integer.toString(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new Replace(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        assertThat(memTracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    void whenFindAllAction() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Item two = memTracker.add(new Item("test2"));
        Input input = new MockInput(new String[] {"0", "1"});
        List<UserAction> actions = Arrays.asList(
                new FindAll(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод всех заявок ===" + ln
                        + one + ln
                        + two + ln
                        + "Меню:" + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindAllActionFailed() {
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        Input input = new MockInput(
                new String[] {"0", "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindAll(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод всех заявок ===" + ln
                        + "Хранилище еще не содержит заявок" + ln
                        + "Меню:" + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindBByIdAction() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        memTracker.add(new Item("test2"));
        Input input = new MockInput(new String[] {"0", String.valueOf(one.getId()), "1"});
        List<UserAction> actions = Arrays.asList(
                new FindById(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать заявку по id" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод заявки по id ===" + ln
                        + one + ln
                        + "Меню:" + ln
                        + "0. Показать заявку по id" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindBByIdActionFailed() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        memTracker.add(new Item("test1"));
        String id = "2";
        Input input = new MockInput(new String[] {"0", id, "1"});
        List<UserAction> actions = Arrays.asList(
                new FindById(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать заявку по id" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод заявки по id ===" + ln
                        + "Заявка с введенным id: " + id + " не найдена" + ln
                        + "Меню:" + ln
                        + "0. Показать заявку по id" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByNameAction() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        memTracker.add(new Item("test2"));
        Input input = new MockInput(new String[] {"0", one.getName(), "1"});
        List<UserAction> actions = Arrays.asList(
                new FindByName(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать заявки по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод заявок по имени ===" + ln
                        + one + ln
                        + "Меню:" + ln
                        + "0. Показать заявки по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByNameActionFailed() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        memTracker.add(new Item("test1"));
        String name = "test";
        Input input = new MockInput(new String[] {"0", "test", "1"});
        List<UserAction> actions = Arrays.asList(
                new FindByName(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать заявки по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод заявок по имени ===" + ln
                        + "Заявки с именем: " + name + " не найдены" + ln
                        + "Меню:" + ln
                        + "0. Показать заявки по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenReplaceItemTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input input = new MockInput(
                new String[] {"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new Replace(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Изменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Редактирование заявки ===" + ln
                        + "Заявка изменена успешно" + ln
                        + "Меню:" + ln
                        + "0. Изменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenReplaceItemTestOutputFailed() {
        Output output = new StubOutput();
        MemTracker memTracker = new MemTracker();
        memTracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input input = new MockInput(
                new String[] {"0", String.valueOf(2), replaceName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new Replace(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Изменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Редактирование заявки ===" + ln
                        + "Ошибка замены заявки" + ln
                        + "Меню:" + ln
                        + "0. Изменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenDeleteItemSuccessfullyOutput() {
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        Item one = memTracker.add(new Item("Deleted item"));
        Input input = new MockInput(
                new String[] {"0", String.valueOf(one.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new Delete(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Удалить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Удаление заявки ===" + ln
                        + "Заявка удалена успешно" + ln
                        + "Меню:" + ln
                        + "0. Удалить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenDeleteItemFailedOutput() {
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        memTracker.add(new Item("Deleted item"));
        Input input = new MockInput(
                new String[] {"0", String.valueOf(2), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new Delete(output),
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Удалить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Удаление заявки ===" + ln
                        + "Ошибка удаления заявки" + ln
                        + "Меню:" + ln
                        + "0. Удалить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenInvalidExit() {
        Output output = new StubOutput();
        Input input = new MockInput(
                new String[] {"3", "0"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new Exit(output)
        );
        new StartUI(output).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Завершить программу" + ln
                        + "Неверный ввод, вы можете выбрать: 0 .. 0" + ln
                        + "Меню:" + ln
                        + "0. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }
}
