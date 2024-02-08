package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book one = new Book("Clean code", 457);
        Book two = new Book("Netty in Action", 296);
        Book three = new Book("Java Concurrency in Practice", 464);
        Book four = new Book("Grokking Algorithms", 256);
        Book[] books = new Book[4];
        books[0] = one;
        books[1] = two;
        books[2] = three;
        books[3] = four;
        for (Book book : books) {
            System.out.println("книга: " + book.getName() + ", количество страниц - " + book.getPages());

        }
        System.out.println();

        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (Book book : books) {
            System.out.println("книга: " + book.getName() + ", количество страниц - " + book.getPages());
        }
        System.out.println();

        for (Book book : books) {
            if ("Clean code".equals(book.getName())) {
                System.out.println("книга: " + book.getName() + ", количество страниц - " + book.getPages());
            }
        }
    }
}
