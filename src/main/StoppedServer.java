package src.main;

import java.io.IOException;
import java.net.Socket;

public class StoppedServer implements ServerState {

    @Override
    public void start(Server server) {
        System.out.println("Starting server...");

        server.setRunning(true);
        server.setState(new RunningServer());   // 🔥 ПЕРЕХІД У НОВИЙ СТАН

        server.startAcceptLoop();               // 🔥 Справжній старт роботи сервера робить Server
    }

    @Override
    public void stop(Server server) {
        System.out.println("Сервер уже зупинений");
    }

    @Override
    public void handleClient(Server server, Socket client) {
        System.out.println("Сервер не працює. Запит відхилено.");
        try {
            client.close();
        } catch (IOException ignored) {}
    }
}
