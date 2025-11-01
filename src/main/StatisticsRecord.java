package src.main;

import java.util.Date;

public class StatisticsRecord {
    private Date date;
    private int totalRequests;
    private int successfulResponses;
    private int errorResponses;

    public StatisticsRecord(Date date, int totalRequests, int successfulResponses, int errorResponses) {
        this.date = date;
        this.totalRequests = totalRequests;
        this.successfulResponses = successfulResponses;
        this.errorResponses = errorResponses;
    }
}

