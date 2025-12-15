package pl.kui3us.model;

public class Book {
    private long id;
    private String title;
    private String author;
    private BookStatus status;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
        this.year = year;
    }


    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", status=" + status +
                '}';
    }
    public void setId(long id) {
        this.id = id;
    }
}
