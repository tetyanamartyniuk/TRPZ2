package src.main;

import java.util.ArrayList;
import java.util.List;


public class DatabaseContext {
    private String connectionString;
    private boolean connected;

    private List<HttpRequest> requests = new ArrayList<>();
    private List<Log> logs = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<StatisticsRecord> statistics = new ArrayList<>();

    public DatabaseContext(String connectionString) {
        this.connectionString = connectionString;
    }

    public void connect() {
        connected = true;
        System.out.println("Connected to database: " + connectionString);
    }

    public void disconnect() {
        connected = false;
        System.out.println("Disconnected from database.");
    }

    public boolean isConnected() {
        return connected;
    }

    public void saveChanges() {
        System.out.println("Changes saved to database.");
    }

   public List<HttpRequest> getRequests() {
        return requests;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<StatisticsRecord> getStatistics() {
        return statistics;
    }


}
