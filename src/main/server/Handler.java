package src.main.server;


import src.main.http.HttpRequest;
import src.main.http.HttpResponse;

public interface Handler {
    public HttpResponse handle(HttpRequest request);
}
