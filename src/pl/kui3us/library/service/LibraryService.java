package pl.kui3us.library.service;

import pl.kui3us.model.Book;
import pl.kui3us.repository.BookRepository;

import java.util.List;

public class LibraryService {




    private BookRepository repository;

    public LibraryService(BookRepository bookRepository) {
        this.repository = bookRepository;
    }


    public List<Book> listBooks (){
        return repository.findAll();
    }

    public Book addBook(String title, String author, int year){
        Book book = new Book(title, author, year);
        repository.addBook(book);
        return book;
    }
}
