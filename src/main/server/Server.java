package src.main.server;


import src.main.http.RouteComponent;
import src.main.repos.LogRepository;
import src.main.repos.RequestBodyRepository;
import src.main.repos.RequestRepository;
import src.main.serverConfigs.ConfigHandler;
import src.main.repos.StatisticsRepository;
import src.main.statistics.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Server {

    private boolean running;
    private ServerSocket serverSocket;

    private final Logger logger = new Logger();

    private ThreadPoolExecutor executorService;
    private final ConfigHandler configHandler;
    private RouteComponent routes;
    private Middleware internalMiddleware;
    private RequestRepository requestRepository;
    private RequestBodyRepository requestBodyRepository;
    private LogRepository logsRepository;
    private StatisticsRepository statisticsRepository;

    public Server() {
        this.configHandler = new ConfigHandler();
    }
    public void setRepositories(
            RequestRepository requestRepository,
            RequestBodyRepository requestBodyRepository,
            LogRepository logsRepository,
            StatisticsRepository statisticsRepository) {

        this.requestRepository = requestRepository;
        this.requestBodyRepository = requestBodyRepository;
        this.logsRepository = logsRepository;
        this.statisticsRepository = statisticsRepository;
    }

    public void start() {
        try {
            if (this.routes == null) {
                throw new IllegalStateException("Some routes must be added before starting the server.");
            }
            running = true;
            logger.info("Starting HTTP server...");
            serverSocket = new ServerSocket(configHandler.getPort(), configHandler.getNumberOfThreads());
            internalMiddleware = new InternalMiddleware(requestRepository, requestBodyRepository, logsRepository, statisticsRepository, routes);
            executorService = new ThreadPoolExecutor(configHandler.getNumberOfThreads(),
                    configHandler.getNumberOfThreads(),
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>());
            logger.info("Http Server started on port" + configHandler.getPort());
            while (running) {
                try {
                    Socket client = serverSocket.accept();
                    executorService.submit(() -> handleRequest(client));
                } catch (IOException e) {
                    if (running) {
                        System.out.println("Error while accepting client: " + e.getMessage());
                    }
                    break;
                }
            }
            } catch(IOException e){
                System.out.println("Error while running server: " + e.getMessage());
            }
        }

    public void handleRequest(Socket socket) {
        try {
            internalMiddleware.invokeRequest(socket);
            socket.close();
        } catch (IOException e) {
            logger.severe("Error handling request: " +
                    e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setRoutes(RouteComponent component) {
        this.routes = component;
    }

    public void stop() {
        try {
            logger.info("Stopping HTTP server...");
            serverSocket.close();
            executorService.shutdown();
            logger.info("HTTP server stopped.");
        }
        catch (IOException e) {
            logger.severe("Error stopping HTTP server: " +
                    e.getMessage());
        }
    };


    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
