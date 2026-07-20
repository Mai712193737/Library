public class PushNotification implements NotificationService {
    private final Logger logger;
    public PushNotification(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void sendNotification(String message) {
        logger.log("Sending PUSH: " + message);
    }
}