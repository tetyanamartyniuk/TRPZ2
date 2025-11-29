package http;

import java.util.HashMap;
import java.util.Map;

class HttpRequestHandler {

    Map<String, HttpRoute> routes = new HashMap<>();

    public HttpResponse handle(HttpRequest request) {
        String path = request.getPath();
        HttpRoute route = routes.get(path);
        HttpResponse response = route.execute(request);
        return response;
    }

    public void addRoute(String path, HttpRoute route) {
        routes.put(path, route);
    }
}
