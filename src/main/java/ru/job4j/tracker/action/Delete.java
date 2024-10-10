package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Output;
import ru.job4j.tracker.Store;

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
    public boolean execute(Input input, Store store) {
        output.println("=== Удаление заявки ===");
        int id = input.askInt("Введите id: ");
        if (store.findById(id) == null) {
            output.println("Ошибка удаления заявки");
        } else {
            store.delete(id);
            output.println(store.findById(id) == null ? "Заявка удалена успешно" : "Ошибка удаления заявки");
        }
        return true;
    }
}
