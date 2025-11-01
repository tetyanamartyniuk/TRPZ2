package src.main;

import java.util.List;

public interface AdminInterface {
    String viewStatistics();
    List<Log> viewLogs();
    void manageServer(HttpServer server);
    void configureServer(HttpServer server, String mode);
}
