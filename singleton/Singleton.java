import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Logger {
    private static Logger instance;

    private Logger() {

    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }
}

public class Singleton {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Starting the application...");

        Logger anotherLogger = Logger.getInstance();

        if (logger == anotherLogger) {
            System.out.println("it's still the same logger!");
        }
    }
}
