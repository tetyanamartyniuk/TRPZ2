package src.main;

import java.util.Date;

public class Log {
    private HttpRequest request;
    private int responseCode;
    private Date timestamp;

    public Log(HttpRequest request, int responseCode) {
        this.request = request;
        this.responseCode = responseCode;
        this.timestamp = new Date();
    }

    public Date getTimestamp() { return timestamp; }
}
