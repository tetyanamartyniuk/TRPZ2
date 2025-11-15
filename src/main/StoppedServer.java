package src.main;

import java.net.ServerSocket;
import java.net.Socket;

public class StoppedServer implements ServerState {

    @Override
    public void start(Server server) {

        try {
            ServerSocket socket = new ServerSocket(server.getPort());
            server.setServerSocket(socket);

            System.out.println("Server started on port " + server.getPort());

            server.setRunning(true);
            server.setState(new RunningServer());

            while (server.isRunning()) {
                Socket client = socket.accept();
                System.out.println("Client connected: " + client.getInetAddress());
                server.handleClient(client);
            }

        } catch (Exception e) {
            if (server.isRunning()) {
                System.out.println("Error during server start: " + e.getMessage());
            }
        } finally {
            System.out.println("Server socket closed.");
        }
    }

    @Override
    public void stop(Server server) {
        System.out.println("Server already stopped");
    }

    @Override
    public void handleClient(Server server, Socket client) {
        System.out.println("Сервер не може приймати запити, адже він зупинений");
    }
}
