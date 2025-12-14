package pl.kui3us.ui;

import pl.kui3us.model.Book;

public class ConsoleApp {
    public static void main(String[] args) {
        Book book = new Book("tytanic", "jan urban", 1998);
        System.out.println(book);

    }
}
