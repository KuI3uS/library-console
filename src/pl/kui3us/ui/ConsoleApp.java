package src.pl.kui3us.ui;

import src.pl.kui3us.library.service.LibraryService;
import src.pl.kui3us.model.Book;
import src.pl.kui3us.repository.BookRepository;

public class ConsoleApp {
    public static void main(String[] args) {


        BookRepository bookRepository = new BookRepository();
        LibraryService service = new LibraryService(bookRepository);

        service.addBook("Wiedzmin", "Andrzej Sapkowski", 1986);

        for (Book book : service.listBooks()){
            System.out.println(book);
        }


    }

}
