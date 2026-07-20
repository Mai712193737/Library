public class SMSNotification implements NotificationService {
    private final Logger logger;

    public SMSNotification(Logger logger) {
        this.logger = logger;
    }
    @Override
    public void sendNotification(String message) {
        logger.log(" Sending SMS: " + message);
    }
}