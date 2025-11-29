package http;

import http.HttpRequest;
import http.HttpResponse;

public interface RouteComponent {
    public HttpResponse handle (HttpRequest request);
    public String getPath();
}
