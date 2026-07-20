public class BorrowService {
    private Logger logger;
    private NotificationService notificationService;

    public BorrowService(Logger logger, NotificationService notificationService) {
        this.logger = logger;
        this.notificationService = notificationService;
    }

    public boolean borrowBook(User user, Book book) {
        if (!book.isAvailable()) {
            System.out.println("Book \"" + book.getTitle() + "\" is not available.");
            return false;
        }
        if (user.getBorrowedBooks().size() >= user.getBorrowLimit()) {
            System.out.println(user.getName() + " reached the borrow limit (" + user.getBorrowLimit() + ").");
            return false;
        }
        book.setStatus(BookStatus.BORROWED);
        user.getBorrowedBooks().add(book);
        logger.log("Book Borrowed: " + book.getTitle() + " by " + user.getName());
        notificationService.sendNotification(user.getName() + " borrowed \"" + book.getTitle() + "\".");
        return true;
    }

    public boolean returnBook(User user, Book book) {
        if (!user.getBorrowedBooks().contains(book)) {
            System.out.println(user.getName() + " did not borrow \"" + book.getTitle() + "\".");
            return false;
        }
        book.setStatus(BookStatus.AVAILABLE);
        user.getBorrowedBooks().remove(book);
        logger.log("Book Returned: " + book.getTitle() + " by " + user.getName());
        notificationService.sendNotification(user.getName() + " returned \"" + book.getTitle() + "\".");
        return true;
    }
}