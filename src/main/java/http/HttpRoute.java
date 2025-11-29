package http;


import http.HttpRequest;
import http.HttpResponse;

public interface HttpRoute {
    HttpResponse execute(HttpRequest request);
}
