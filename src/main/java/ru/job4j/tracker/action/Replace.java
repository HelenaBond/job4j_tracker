package ru.job4j.tracker.action;

import ru.job4j.tracker.*;

public class Replace implements UserAction {
    private Output output;

    public Replace(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Изменить заявку";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        output.println("=== Редактирование заявки ===");
        int id = input.askInt("Введите id: ");
        String name = input.askStr("Введите имя: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            output.println("Заявка изменена успешно");
        } else {
            output.println("Ошибка замены заявки");
        }
        return true;
    }
}
