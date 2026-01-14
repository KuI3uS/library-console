package pl.kui3us.ui;

import pl.kui3us.library.service.LibraryService;
import pl.kui3us.model.Book;
import pl.kui3us.repository.BookRepository;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean choice = true;

        BookRepository bookRepository = new BookRepository();
        LibraryService service = new LibraryService(bookRepository);

        while (choice){
            System.out.println("Menu");
            System.out.println("1 -> add flow");
            System.out.println("2 -> list flow");
            System.out.println("3 -> search by title");
            System.out.println("0 -> return");
            String chase = scanner.nextLine();

            switch (chase){
                case "1" -> {
                    System.out.println("title : ");
                    String title = scanner.nextLine();
                    System.out.println("author : ");
                    String author = scanner.nextLine();

                    int year = readYear(scanner, "year :");
                    service.addBook(title, author, year);

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
                case "3" -> {
                    System.out.print("Enter title fragment: ");
                    String query = scanner.nextLine();
                    List<Book> results = service.searchByTitle(query);
                    if(results.isEmpty()){
                        System.out.println("No books found for given title.");
                    }
                    else {
                        for(Book book : results){
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

    private static int readYear(Scanner scanner, String prompt){


        while (true){
            System.out.print(prompt);
            String test = scanner.nextLine();
            try {
                int year = Integer.parseInt(test);
                if (year <= 0){
                    System.out.println("Year must be greater than 0");
                    continue;
                }
                return year;
            } catch (NumberFormatException e){
                System.out.println("Invalid number. Please enter a valid year");
            }
        }
    }
}
