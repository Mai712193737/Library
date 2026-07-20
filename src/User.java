import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String name;
    private int userID;
    private String email;
    private List<Book> borrowedBooks;

    public User(String name, int userID, String email) {
        this.name = name;
        this.userID = userID;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public abstract int getBorrowLimit();

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }
}