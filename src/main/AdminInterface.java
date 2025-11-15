package src.main;

import java.net.Socket;
import java.util.List;

public interface AdminInterface {
//    String viewStatistics();
//
//    List<Log> viewLogs();

    void manageServer(Server server);

    boolean isServerRunning(Server server);

    void handleClient(Server server, Socket client);
}
