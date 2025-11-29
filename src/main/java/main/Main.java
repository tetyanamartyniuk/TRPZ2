package main;
import auth.AuthService;
import database.DatabaseConnection;
import database.DatabaseHandler;
import http.FileHandler;
import http.RouteComposite;
import http.RouteLeaf;
import database.repos.LogRepository;
import database.repos.RequestBodyRepository;
import database.repos.RequestRepository;
import database.repos.RequestRepositoryImpl;
import routes.DynamicRoute;
import serverConfigs.ConfigHandler;
import routes.HelloRoute;
import routes.adminRoutes.AdminLoginHandlerRoute;
import routes.adminRoutes.AdminLoginRoute;
import routes.adminRoutes.AdminStatsRoute;
import database.repos.ServerStatisticsService;
import database.repos.StatisticsRepository;
import server.Server;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ConfigHandler config = new ConfigHandler();
        DatabaseConnection db = new DatabaseConnection(config.getDatabaseUrl(), config.getDatabaseUser(),
                config.getDatabasePassword()
        );
        DatabaseHandler handler = new DatabaseHandler(db);
        handler.createTable();
        AuthService auth = new AuthService();
        RequestRepository requestRepository = new RequestRepositoryImpl(db);
        RequestBodyRepository requestBodyRepository = new RequestBodyRepository(db);
        LogRepository logsRepository = new LogRepository(db);
        StatisticsRepository statisticsRepository = new ServerStatisticsService(db);

        Server server = new Server();
        RouteComposite root = new RouteComposite("/");
        RouteComposite admin = new RouteComposite("/admin");
        admin.addChild(new RouteLeaf("/login", new AdminLoginRoute()));
        admin.addChild(new RouteLeaf("/authenticate", new AdminLoginHandlerRoute(auth)));
        admin.addChild(new RouteLeaf("/stats", new AdminStatsRoute(statisticsRepository)));

        root.addChild(admin);
        server.setRoutes(root);
        server.setRepositories(requestRepository, requestBodyRepository, logsRepository, statisticsRepository
        );
        server.start();
    }}


