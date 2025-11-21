package src.main;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestHandler {

    Map<String, HttpRoute> routes = new HashMap<>();

    public HttpResponse handle(HttpRequest request) {

        String path = request.getPath();

        HttpRoute route = routes.get(path);

        if (route != null) {
            return route.execute(request);
        }

        return HttpResponseDirector.NotFound(request.getHeaders());
    }

    public void addRoute(String path, HttpRoute route){
        routes.put(path, route);
    }

}

