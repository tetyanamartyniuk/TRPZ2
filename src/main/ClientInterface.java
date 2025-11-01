package src.main;

public interface ClientInterface {
    void connect(HttpServer server);
    HttpResponse sendRequest(HttpRequest request);
}
