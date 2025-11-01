package src.main;

public class RequestHandler {
    private HttpServer server;

    public RequestHandler(HttpServer server) {
        this.server = server;
    }

    public HttpResponse handle(HttpRequest request) {
        return new HttpResponse(200, "Request handled successfully");
    }
}

