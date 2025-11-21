package src.main;

public class ServerManagment {
    public static void main(String[] args) {
        Server server = new Server(8083);
        RouteComposite routeComposite = new RouteComposite("/hello");
        routeComposite.addChild(new RouteLeaf("/world", new HelloRoute()));
        server.setRoutes(routeComposite);
        server.start();
    }

}


