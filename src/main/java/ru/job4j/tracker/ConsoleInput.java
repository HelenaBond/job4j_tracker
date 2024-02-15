package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Output output;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleInput(Output output) {
        this.output = output;
    }

    @Override
    public String askStr(String question) {
        output.println(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        output.println(question);
        return Integer.parseInt(scanner.nextLine());
    }
}
