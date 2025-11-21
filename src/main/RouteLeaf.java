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
        if (route == null ||
                !request.getPath().replaceAll("/", "")
                        .equals(path.replaceAll("/", ""))) {
            return null;
        }
        return route.execute(request);
    }

    @Override
    public String getPath() {
        return path;
    }
}
