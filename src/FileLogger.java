import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private final String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public void log(String message) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("FileLogger error: " + e.getMessage());
        }
    }
}