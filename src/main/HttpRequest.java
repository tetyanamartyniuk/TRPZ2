package src.main;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String method;
    private String path;
    private Map<String, String> headers;
    private String body;

    public HttpRequest(String method, String path, Map<String, String> headers, String body) {
        this.method = method;
        this.path = path;
        this.body = body;
        this.headers = headers != null ? new HashMap<>(headers) : new HashMap<>();
    }
    public String getMethod() { return method; }
    public String getBody() { return body; }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(String name) {
        return headers.getOrDefault(name, null);
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public String getPath() {
        if (path == null) return "/";

        int questionIndex = path.indexOf('?');
        if (questionIndex != -1) {
            return path.substring(0, questionIndex);
        }

        return path;
    }
}