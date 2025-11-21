package src.main;

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

    private String stamp() {
        return LocalDateTime.now().format(fmt);
    }
}

