package src.main;

import src.main.repos.LogRepository;
import src.main.repos.RequestBodyRepository;
import src.main.repos.RequestRepository;
import src.main.repos.StatisticsRepository;

// Імпортуйте ваші інтерфейси/класи репозиторіїв
// import src.main.repositories.RequestRepository;
// import src.main.repositories.RequestBodyRepository;
// import src.main.repositories.LogRepository;

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

