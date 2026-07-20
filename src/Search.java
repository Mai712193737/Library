import java.util.List;

public interface Search {
    List<Book> search(List<Book> books, String keyword);
}