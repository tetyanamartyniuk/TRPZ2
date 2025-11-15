package src.main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.List;

public class AdminPanel implements AdminInterface {
    private final Server server;

    public AdminPanel(Server server) {
        this.server = server;
    }

//    @Override
//    public String viewStatistics() {
//        return statistics.getStats();
//    }
//
//    @Override
//    public List<Log> viewLogs() {
//        return statistics.getLogs();
//    }

    @Override
    public void manageServer(Server server) {
        if (!server.isRunning()) {
            new Thread(server::start).start(); // стартуємо сервер
        } else {
            server.stop(); // зупиняємо сервер
        }
    }

    @Override
    public boolean isServerRunning(Server server) {
        return server.isRunning();
    }

    @Override
    public void handleClient(Server server, Socket client) {
        server.handleClient(client);             // делегує обробку клієнта серверу
    }


}
