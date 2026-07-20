public class EmailNotification implements NotificationService {
    private final Logger logger;

    public EmailNotification(Logger logger) {
        this.logger = logger;
    }
    @Override
    public void sendNotification(String message) {
        logger.log("Sending EMAIL: " + message);
    }
}