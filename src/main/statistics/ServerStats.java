package src.main.statistics;

import java.util.Map;

public class ServerStats {
    private final long successfulRequests;
    private final long clientErrors;

    private long serverErrors;
    private long totalRequests;
    private long avgResponseTimeMs;

    public ServerStats(long totalRequests,
                       long avgResponseTimeMs,
                       long successfulRequests,
                       long clientErrors,
                       long serverErrors) {
        this.totalRequests = totalRequests;
        this.avgResponseTimeMs = avgResponseTimeMs;
        this.successfulRequests = successfulRequests;
        this.clientErrors = clientErrors;
        this.serverErrors = serverErrors;
    }

    public long getTotalRequests() { return totalRequests; }
    public long getAvgResponseTimeMs() { return avgResponseTimeMs; }
    public long getSuccessfulRequests() { return successfulRequests; }

    public long getClientErrors() {return clientErrors; }
    public long getServerErrors(){return serverErrors; }
}

