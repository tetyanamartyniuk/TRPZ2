package src.main;

public class RouteLeaf implements RouteComponent {

    String path;
    HttpRoute route;

    public RouteLeaf(String path,  HttpRoute route) {
        this.path = path;
        this.route = route;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        if (!request.getPath().equals(path)) {
            return null;
        }
        return route.execute(request);
    }


    public HttpRoute getHandler() {
        return route;
    }

    @Override
    public String getPath() {
        return path;
    }
}
