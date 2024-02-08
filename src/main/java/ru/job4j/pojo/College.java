package ru.job4j.pojo;

import java.time.LocalDate;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Бондарева Елена Викторовна");
        student.setGroup("БЗ-311");
        student.setReceiptDate(LocalDate.now());
        System.out.println("студент: " + student.getFullName()
                + ", группа: " + student.getGroup()
                + ", дата зачисления: " + student.getReceiptDate());
    }
}
