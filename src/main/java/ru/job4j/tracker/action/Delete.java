package ru.job4j.tracker.action;

import ru.job4j.tracker.*;

public class Delete implements UserAction {
    private final Output output;

    public Delete(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Удалить заявку";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        output.println("=== Удаление заявки ===");
        int id = input.askInt("Введите id: ");
        output.println(tracker.delete(id) ? "Заявка удалена успешно" : "Ошибка удаления заявки");
        return true;
    }
}
