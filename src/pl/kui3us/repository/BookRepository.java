package src.pl.kui3us.repository;

import src.pl.kui3us.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository  {

    private long nextId = 1;

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        book.setId(nextId);
        books.add(book);
        nextId++;


    }
    public List<Book> findAll() {
        return books;
    }

}
