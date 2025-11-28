package src.main.http;

import src.main.http.HttpRequest;
import src.main.http.HttpResponse;

public interface RouteComponent {
    public HttpResponse handle (HttpRequest request);
    public String getPath();
}
