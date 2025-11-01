package src.main;


import java.util.List;

public class AdminPanel implements AdminInterface {
    private HttpServer server;
    private Statistics statistics;

    public AdminPanel(HttpServer server, Statistics statistics) {
        this.server = server;
        this.statistics = statistics;
    }

    @Override
    public String viewStatistics() {
        return statistics.getStats();
    }

    @Override
    public List<Log> viewLogs() {
        return statistics.getLogs();
    }

    @Override
    public void manageServer(HttpServer server) {
        this.server = server;
        server.start();
        System.out.println("Server started by admin.");
    }

    @Override
    public void configureServer(HttpServer server, String mode) {
        this.server = server;
        server.configure(mode);
        System.out.println("Server mode changed to: " + mode);
    }
}
