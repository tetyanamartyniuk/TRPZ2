package src.main;

import java.util.ArrayList;
import java.util.List;

public
class HttpServer {

    private ServerState state;

    public HttpServer() {
        this.state = new StoppedServer(this);
    }

    public void setState(ServerState state) {
        this.state = state;
    }
    public void start() { state.start(); }
    public void stop() { state.stop(); }
    public void handleRequest() { state.handleRequest(); }
    private int port;
    private List<RequestHandler> handlers = new ArrayList<>();
    private Statistics statistics;
    private String mode;

    public HttpServer(int port, Statistics statistics) {
        this.port = port;
        this.statistics = statistics;
        this.state = new StoppedServer(this);

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
