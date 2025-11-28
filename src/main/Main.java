package src.main;

import src.main.auth.AuthService;
import src.main.database.DatabaseConnection;
import src.main.database.DatabaseHandler;
import src.main.http.FileHandler;
import src.main.http.RouteComposite;
import src.main.http.RouteLeaf;
import src.main.repos.LogRepository;
import src.main.repos.RequestBodyRepository;
import src.main.repos.RequestRepository;
import src.main.repos.RequestRepositoryImpl;
import src.main.routes.DynamicRoute;
import src.main.serverConfigs.ConfigHandler;
import src.main.routes.HelloRoute;
import src.main.routes.adminRoutes.AdminLoginHandlerRoute;
import src.main.routes.adminRoutes.AdminLoginRoute;
import src.main.routes.adminRoutes.AdminStatsRoute;
import src.main.statistics.ServerStatisticsService;
import src.main.repos.StatisticsRepository;
import src.main.server.Server;


public class Main {

    public static void main(String[] args) throws InterruptedException {

         ConfigHandler config = new ConfigHandler();

        DatabaseConnection db = new DatabaseConnection(
                config.getDatabaseUrl(),
                config.getDatabaseUser(),
                config.getDatabasePassword()
        );


        DatabaseHandler handler = new DatabaseHandler(db);
        handler.createTable();

        AuthService auth = new AuthService();

        FileHandler fileHandler = new FileHandler();

        RequestRepository requestRepository = new RequestRepositoryImpl(db);
        RequestBodyRepository requestBodyRepository = new RequestBodyRepository(db);
        LogRepository logsRepository = new LogRepository(db);

        StatisticsRepository statisticsRepository = new ServerStatisticsService(db);

        Server server = new Server();

        RouteComposite root = new RouteComposite("/");

        root.addChild(new RouteLeaf("/hello", new HelloRoute(fileHandler)));
        root.addChild(new RouteLeaf("/dynamic", new DynamicRoute()));

        RouteComposite admin = new RouteComposite("/admin");

        admin.addChild(new RouteLeaf("/login", new AdminLoginRoute()));

        admin.addChild(new RouteLeaf("/authenticate", new AdminLoginHandlerRoute(auth)));

        admin.addChild(new RouteLeaf("/stats", new AdminStatsRoute(statisticsRepository)));

        root.addChild(admin);

        server.setRoutes(root);

        server.setRepositories(
                requestRepository,
                requestBodyRepository,
                logsRepository,
                statisticsRepository
        );

        server.start();


    }
}




