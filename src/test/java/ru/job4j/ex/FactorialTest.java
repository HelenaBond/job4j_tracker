package ru.job4j.ex;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactorialTest {
    @Test
    public void whenMinusOneThenException() {
        int number = -1;
        Factorial factorial = new Factorial();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> factorial.calc(number));
        assertThat(exception.getMessage()).isEqualTo("Number could not be less than 0");
    }

    @Test
    public void whenOneThenOne() {
        int number = 1;
        Factorial factorial = new Factorial();
        int actual = factorial.calc(number);
        int expected = 1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenTwoThenTwo() {
        int number = 2;
        Factorial factorial = new Factorial();
        int actual = factorial.calc(number);
        int expected = 2;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenThreeThenSix() {
        int number = 3;
        Factorial factorial = new Factorial();
        int actual = factorial.calc(number);
        int expected = 6;
        assertThat(actual).isEqualTo(expected);
    }
}