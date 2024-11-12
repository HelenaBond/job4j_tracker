package ru.job4j.tracker;

import ru.job4j.tracker.action.*;
import ru.job4j.tracker.action.profile.CreateManyItems;
import ru.job4j.tracker.action.profile.DeleteAllItems;
import ru.job4j.tracker.store.MemTracker;
import ru.job4j.tracker.store.Store;

import java.util.Arrays;
import java.util.List;

public class StartUI {
    private final Output output;

    public StartUI(Output output) {
        this.output = output;
    }

    public void init(Input input, Store store, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Выбрать: ");
            if (select < 0 || select >= actions.size()) {
                output.println("Неверный ввод, вы можете выбрать: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, store);
        }
    }

    private void showMenu(List<UserAction> actions) {
        output.println("Меню:");
        for (int i = 0; i < actions.size(); i++) {
            output.println(i + ". " + actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput(output));
        Store store = new MemTracker();
            List<UserAction> actions = Arrays.asList(
                    new CreateManyItems(output),
                    new DeleteAllItems(output),
                    new Create(output),
                    new FindAll(output),
                    new Replace(output),
                    new Delete(output),
                    new FindById(output),
                    new FindByName(output),
                    new Exit(output)
            );
            new StartUI(output).init(input, store, actions);
    }
}
