package src.main;

import java.net.Socket;

public class RunningServer implements ServerState {

    @Override
    public void start(Server server) {
        System.out.println("Сервер уже працює");
    }

    @Override
    public void stop(Server server) {
        System.out.println("Зупиняємо сервер...");

        // сервер більше не приймає нових клієнтів
        server.setRunning(false);

        // закриваємо сервер-сокет якщо ще відкритий
        try {
            if (server.getServerSocket() != null && !server.getServerSocket().isClosed()) {
                server.getServerSocket().close();
            }
        } catch (Exception ignored) {}

        // міняємо стан на StoppedServer
        server.setState(new StoppedServer());
    }

    @Override
    public void handleClient(Server server, Socket client) {
        // делегуємо обробку справжньому серверу
        server.handleClient(client);
    }
}
