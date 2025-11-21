package src.main;

import java.util.Map;

public class InternalMiddleware extends Middleware{
    RouteComponent routes;
    public InternalMiddleware(RouteComponent routes){
        this.routes = routes;
    }
    @Override
    public Handler createHandler() {
        return new InternalHandler(routes);
    }
}


