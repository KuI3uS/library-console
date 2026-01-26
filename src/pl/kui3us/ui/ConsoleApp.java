package pl.kui3us.ui;

import pl.kui3us.library.service.LibraryService;
import pl.kui3us.model.Book;
import pl.kui3us.repository.BookRepository;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

import static java.awt.SystemColor.text;

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
            System.out.println("4 -> search by author");
            System.out.println("5 -> remove by ID");
            System.out.println("6 -> remove by title");
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
                case "4" -> {
                    System.out.print("Enter Author fragment: ");
                    String query = scanner.nextLine();
                    List<Book> results = service.searchByAuthor(query);
                    if(results.isEmpty()){
                        System.out.println("No books found for given author.");
                    }
                    else {
                        for(Book book : results){
                            System.out.println(book);
                        }
                    }
                }
                case "5" -> {
                    long id = readId(scanner , "to delete a book, enter its ID: ");

                    System.out.print("Are you sure you want to delete this book? (T/N): ");
                    String answer = scanner.nextLine().trim().toLowerCase();

                    if(!answer.equals("t")){
                        System.out.println("Deletion cancelled.");
                        break;
                    }

                    boolean deleted = service.deleteBookById(id);

                    if (deleted) {
                        System.out.println("Book has been deleted.");
                    } else {
                        System.out.println("Book not found.");
                    }

                }
                case "6" -> {
                    System.out.println("What book title do you want to remove?");
                    String query = scanner.nextLine().trim();

                    List<Book> matches = service.searchByTitle(query);

                    if (matches.isEmpty()){
                        System.out.println("No books found");
                    }
                    //Przypadek 1 wynik
                    else if(matches.size()== 1){
                        Book book = matches.get(0);
                        System.out.println(book);

                        System.out.print("Are you sure you want to delete this book? (T/N): ");
                        String answer = scanner.nextLine().trim().toLowerCase();

                        if (!answer.equals("t")) {
                            System.out.println("Deletion cancelled.");
                            break;
                        }
                        boolean deleted = service.deleteBookById(book.getId());

                        if (deleted) {
                            System.out.println("Book has been deleted.");
                        } else {
                            System.out.println("Book not found.");
                        }

                    }
                    else if(matches.size() > 1) {
                        System.out.println("Multiple books found:");
                        for (Book book : matches) {
                            System.out.println(book);
                        }
                        long id = readId(scanner, "Enter ID of the book to delete: ");

                        System.out.print("Are you sure you want to delete this book? (T/N): ");
                        String answer = scanner.nextLine().trim().toLowerCase();

                        if(!answer.equals("t")){
                            System.out.println("Deletion cancelled.");
                            break;
                        }
                        boolean deleted = service.deleteBookById(id);

                        if (deleted) {
                            System.out.println("Book has been deleted.");
                        } else {
                            System.out.println("Book not found.");
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
    private static long readId(Scanner scanner, String prompt){
        while (true){
            System.out.println(prompt);
            String test = scanner.nextLine().trim();
            try {
                long id = Long.parseLong(test);
                if(id <= 0){
                    System.out.println("Id musi być wieksze niż 0");
                    continue;
                }
                return id;

            }catch (NumberFormatException e){
                System.out.println("Invalid number. Please enter a valid Id");
            }
        }
    }
}
