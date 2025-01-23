package src;

public class Book {
    private final String title;
    private final String author;
    private boolean isAvailable;
    private final String rackId;
    private final String bookId;
    private final String shelfId;
    private final BookGenre genre;

    public Book(String title, String author, String rackId, String bookId, String shelfId, BookGenre genre) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.rackId = rackId;
        this.bookId = bookId;
        this.shelfId = shelfId;
        this.genre = genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookGenre getGenre() {
        return genre;
    }
}
