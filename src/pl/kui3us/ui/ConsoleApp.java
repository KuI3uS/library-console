package pl.kui3us.ui;

import pl.kui3us.model.Book;
import pl.kui3us.repository.BookRepository;

public class ConsoleApp {
    public static void main(String[] args) {


        BookRepository bookRepository = new BookRepository();

        bookRepository.addBook(new Book("tytanic", "jan urban", 1998));
        bookRepository.addBook(new Book("arka", "jony brawo", 2002));

        bookRepository.findAll();
        for (Book book : bookRepository.findAll()) {
            System.out.println(book);
        }
    }
}
