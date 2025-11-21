package src.main;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class RouteComposite implements RouteComponent{
    List<RouteComponent> componentList = new ArrayList<>();
    String path;

    public RouteComposite(String path){
        this.path = path;
    }

    @Override
    public HttpResponse handle(HttpRequest request){
        String requestPath = request.getPath();

        if(!requestPath.startsWith(path)){
            return null;
        }

        String remainingPath = requestPath.substring(path.length());

        for (RouteComponent component : componentList) {
            if (remainingPath.startsWith(component.getPath())) {
                return component.handle(
                        new HttpRequest(
                                request.getMethod(),
                                remainingPath,
                                request.getHeaders(),
                                request.getBody()
                        )
                );
            }
        }

        return null;

    }
    @Override
    public String getPath() {
        return path;
    }

    public void addChild(RouteComponent component) {
        if (componentList.stream().anyMatch(c -> c.getPath() == component.getPath())) {
            throw new IllegalArgumentException("Path is already taken");
        }
        componentList.add(component);
    }

    public void removeChild(RouteComponent component) {
        componentList.remove(component);
    }

    public RouteComponent getChild(String path) {
        return componentList.stream()
                .filter(c -> c.getPath() == path)
                .findFirst()
                .orElse(null);
    }

}
