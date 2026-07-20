import java.util.ArrayList;
import java.util.List;

public class BookService {
    private List<Book> books;
    private Logger logger;

    public BookService(Logger logger) {
        this.books = new ArrayList<>();
        this.logger = logger;
    }

    public void addBook(Book book) {
        books.add(book);
        logger.log("Book Added: " + book.getTitle());
    }

    public void removeBook(Book book) {
        books.remove(book);
        logger.log("Book Removed: " + book.getTitle());
    }

    public List<Book> displayAllBooks() {
        return books;
    }
}