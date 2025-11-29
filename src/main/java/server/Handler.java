package server;


import http.HttpRequest;
import http.HttpResponse;

public interface Handler {
    public HttpResponse handle(HttpRequest request);
}
