import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static Logger logger = new ConsoleLogger();
    private static NotificationService notificationService = new EmailNotification(logger);
    private static BookService bookService = new BookService(logger);
    private static BorrowService borrowService = new BorrowService(logger, notificationService);
    private static Library library = new Library();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    removeBook();
                    break;
                case "3":
                    displayAllBooks();
                    break;
                case "4":
                    searchBook();
                    break;
                case "5":
                    addUser();
                    break;
                case "6":
                    displayAllUsers();
                    break;
                case "7":
                    borrowBook();
                    break;
                case "8":
                    returnBook();
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Library Management System =====");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Display All Books");
        System.out.println("4. Search Book");
        System.out.println("5. Add User (Student/Teacher)");
        System.out.println("6. Display All Users");
        System.out.println("7. Borrow Book");
        System.out.println("8. Return Book");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addBook() {
        System.out.print("Book ID: ");
        int id = readInt();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();

        Book book = new Book(id, title, author, category);
        bookService.addBook(book);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }

    private static void removeBook() {
        System.out.print("Enter Book ID to remove: ");
        int id = readInt();
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        bookService.removeBook(book);
        System.out.println("Book removed successfully.");
    }
    private static void displayAllBooks() {
        List<Book> books = bookService.displayAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("---- All Books ----");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private static void searchBook() {
        System.out.println("Search by: 1) Title  2) Author  3) Category");
        String option = scanner.nextLine().trim();
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();
        Search search;
        switch (option) {
            case "1":
                search = new SearchByTitle();
                break;
            case "2":
                search = new SearchByAuthor();
                break;
            case "3":
                search = new SearchByCategory();
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        List<Book> results = search.search(bookService.displayAllBooks(), keyword);
        if (results.isEmpty()) {
            System.out.println("No matching books found.");
        } else {
            System.out.println("---- Search Results ----");
            for (Book b : results) {
                System.out.println(b);
            }
        }
    }

    //  Users

    private static void addUser() {
        System.out.println("User type: 1) Student  2) Teacher");
        String type = scanner.nextLine().trim();

        System.out.print("User ID: ");
        int id = readInt();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        User user;
        if (type.equals("1")) {
            user = new Student(name, id, email);
        } else if (type.equals("2")) {
            user = new Teacher(name, id, email);
        } else {
            System.out.println("Invalid user type.");
            return;
        }

        library.addUser(user);
        System.out.println("User added successfully.");
    }

    private static void displayAllUsers() {
        List<User> users = library.getUsers();
        if (users.isEmpty()) {
            System.out.println("No users available.");
            return;
        }
        System.out.println("---- All Users ----");
        for (User u : users) {
            System.out.println("[" + u.getUserID() + "] " + u.getName() + " - " + u.getEmail()
                    + " (" + u.getClass().getSimpleName() + ", limit=" + u.getBorrowLimit() + ")");
        }
    }

    // ---------- Borrow / Return ----------

    private static void borrowBook() {
        System.out.print("Enter User ID: ");
        int userId = readInt();
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter Book ID: ");
        int bookId = readInt();
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        borrowService.borrowBook(user, book);
    }

    private static void returnBook() {
        System.out.print("Enter User ID: ");
        int userId = readInt();
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter Book ID: ");
        int bookId = readInt();
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        borrowService.returnBook(user, book);
    }

    // ---------- Helpers ----------

    private static Book findBookById(int id) {
        for (Book b : bookService.displayAllBooks()) {
            if (b.getBookID() == id) {
                return b;
            }
        }
        return null;
    }

    private static User findUserById(int id) {
        for (User u : library.getUsers()) {
            if (u.getUserID() == id) {
                return u;
            }
        }
        return null;
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}