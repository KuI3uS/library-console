package pl.kui3us.ui;

import pl.kui3us.library.service.LibraryService;
import pl.kui3us.model.Book;
import pl.kui3us.repository.BookRepository;

import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        boolean choice = true;

        BookRepository bookRepository = new BookRepository();
        LibraryService service = new LibraryService(bookRepository);

        service.addBook("Wiedzmin", "Andrzej Sapkowski", 1986);


        while (choice){
            System.out.println("Menu");
            System.out.println("1 -> add flow");
            System.out.println("2 -> list flow");
            System.out.println("0 -> return");
            String chase = scanner.nextLine();

            switch (chase){
                case "1" -> {
                    System.out.println("title : ");
                    String title = scanner.nextLine();
                    System.out.println("author : ");
                    String author = scanner.nextLine();
                    System.out.println("year : ");
                    String yearText = scanner.nextLine();
                    try {
                        int year = Integer.parseInt(yearText);
                        service.addBook(title, author, year);

                    }catch (NumberFormatException e){
                        System.out.println("Incorrect year. Try again first.");
                    }

                }
                case "2" -> {
                    var books = service.listBooks();
                    if (books.isEmpty()){
                        System.out.println("No books in the catalog.");
                    }
                    else {
                        for(Book book : books){
                            System.out.println(book);
                        }
                    }

                }
                case "0" -> {
                    return;
                }
            }

        }

    }

}
