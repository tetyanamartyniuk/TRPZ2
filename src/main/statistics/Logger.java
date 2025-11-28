package src.main.statistics;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final DateTimeFormatter fmt =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void info(String msg) {
        System.out.println("[INFO]  " + stamp() + " | " + msg);
    }

    public void warning(String msg) {
        System.out.println("[WARN]  " + stamp() + " | " + msg);
    }

    public void error(String msg) {
        System.out.println("[ERROR] " + stamp() + " | " + msg);
    }
    public void severe(String msg) {
        System.err.println("[SEVERE] " + stamp() + " | " + msg); // ВИПРАВЛЕНО: Використання System.err
    }

    private String stamp() {
        return LocalDateTime.now().format(fmt);
    }
}
