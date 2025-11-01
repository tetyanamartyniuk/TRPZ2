package src.main;

import java.util.List;

public class Statistics {
    private int requestCount;
    private LogRepository logRepository;

    public Statistics(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void logRequest(HttpRequest request, HttpResponse response) {
        logRepository.add(new Log(request, response.getStatusCode()));
        requestCount++;
    }

    public String getStats() {
        return "Total requests: " + requestCount;
    }

    public List<Log> getLogs() {
        return logRepository.getAll();
    }
}
