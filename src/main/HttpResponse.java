package src.main;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private int statusCode;
    private Map<String, String> headers = new HashMap<>();
    private String body;

    public HttpResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() { return statusCode; }
    public String getBody() { return body; }
}