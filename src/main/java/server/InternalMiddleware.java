package server;

import http.RouteComponent;
import database.repos.LogRepository;
import database.repos.RequestBodyRepository;
import database.repos.RequestRepository;
import database.repos.StatisticsRepository;



public class InternalMiddleware extends Middleware {

    private final RouteComponent routes;

    public InternalMiddleware(
            RequestRepository requestRepository,
            RequestBodyRepository requestBodyRepository,
            LogRepository logsRepository,
            StatisticsRepository statisticsRepository,
            RouteComponent routes)
    {
        super(requestRepository, requestBodyRepository, logsRepository, statisticsRepository);
        this.routes = routes;
    }

    @Override
    public Handler createHandler() {
        return new InternalHandler(routes);
    }
}

