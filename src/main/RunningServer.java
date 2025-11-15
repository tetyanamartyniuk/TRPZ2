package src.main;

import java.net.Socket;

public class RunningServer implements ServerState {

    @Override
    public void start(Server server) {
        System.out.println("Сервер уже працює");
    }

    @Override
    public void stop(Server server) {
        System.out.println("Stopping server...");
        server.setRunning(false);

        try {
            if (server.getServerSocket() != null) {
                server.getServerSocket().close();
            }
        } catch (Exception ignored) {}

        server.setState(new StoppedServer());
    }

    @Override
    public void handleClient(Server server, Socket client) {
        new Thread(() -> server.processClient(client)).start();
    }
}
