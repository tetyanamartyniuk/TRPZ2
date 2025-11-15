package src.main;

import java.util.Map;

public class RequestHandler {
    private Server server;

    public RequestHandler(Server server) {
        this.server = server;
    }

    public HttpResponse handle(HttpRequest request) {
        Map<String,String> headers = Map.of("Content-Type", "text/plain; charset=UTF-8");
        return HttpResponseDirector.Ok("Request handled successfully", headers);
    }
}


