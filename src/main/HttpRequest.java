package src.main;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String method;
    private String url;
    private Map<String, String> headers = new HashMap<>();
    private String body;

    public HttpRequest(String method, String url, String body) {
        this.method = method;
        this.url = url;
        this.body = body;
    }
    public String getMethod() { return method; }
    public String getUrl() { return url; }
    public String getBody() { return body; }
}