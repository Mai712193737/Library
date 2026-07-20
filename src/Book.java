public class Book {
    private int bookID;
    private String title;
    private String author;
    private String category;
    private BookStatus status;

    public Book(int bookID, String title, String author, String category) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = BookStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return this.status == BookStatus.AVAILABLE;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public BookStatus getStatus() {
        return status;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        return bookID == ((Book) o).bookID;
    }

    @Override
    public String toString() {
        return "[" + bookID + "] " + title + " - " + author + " (" + category + ") - " + status;
    }
}