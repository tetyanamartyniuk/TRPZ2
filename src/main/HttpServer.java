package src.main;

import java.util.ArrayList;
import java.util.List;

public
class HttpServer {
    private int port;
    private List<RequestHandler> handlers = new ArrayList<>();
    private Statistics statistics;
    private String mode;

    public HttpServer(int port, Statistics statistics) {
        this.port = port;
        this.statistics = statistics;
    }

    public void start() {
        System.out.println("Server started on port " + port);
    }

    public void stop() {
        System.out.println("Server stopped.");
    }

    public HttpResponse handleRequest(HttpRequest request) {
        for (RequestHandler handler : handlers) {
            HttpResponse response = handler.handle(request);
            statistics.logRequest(request, response);
            return response;
        }
        return new HttpResponse(404, "Not Found");
    }

    public void configure(String mode) {
        this.mode = mode;
        System.out.println("Server mode set to: " + mode);
    }

    public void addHandler(RequestHandler handler) {
        handlers.add(handler);
    }
}
