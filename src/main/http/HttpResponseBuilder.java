package src.main.http;

import java.util.Map;

public class HttpResponseBuilder {
    private String status;
    private Map<String, String> headers;
    private String body;

    public void buildStatus(String status) {
        this.status = status;
    }

    public void buildHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void buildBody(String body) {
        this.body = body;
    }

    public HttpResponse build() {
        return new HttpResponse(status, headers, body);
    }
}
