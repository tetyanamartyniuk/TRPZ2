package src.main;

public class StoppedServer extends ServerState{
    public StoppedServer(HttpServer server) {
        super(server);
    }

    public void start() {
        System.out.println("Запускаємо сервер");
        server.setState(new RunningServer(server));
    }
    public void stop() {
        System.out.println("Сервер вже зупинений");
    }
    public void handleRequest() {
        System.out.println("Не можна обробляти запити, коли сервер зупинений");
    }
}

