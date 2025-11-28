package src.main.http;


import src.main.http.HttpRequest;
import src.main.http.HttpResponse;

public interface HttpRoute {
    HttpResponse execute(HttpRequest request);
}
