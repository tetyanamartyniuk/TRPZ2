package src.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    private int port;

    private boolean running;
    private ServerState state = new StoppedServer();
    private ServerSocket serverSocket;

    private RouteComponent routes;
    private Middleware internalMiddleware;

    public Server(int port) {
        this.port = port;
    }

    public void setRoutes(RouteComponent routes) {
        this.routes = routes;
    }

    public void start(){
        state.start(this);

    }

    public void startAcceptLoop() {
        try {
            serverSocket = new ServerSocket(port);
            internalMiddleware = new InternalMiddleware(routes);

            System.out.println("Server started on port " + port);

            while (running) {
                Socket client = serverSocket.accept();
                state.handleClient(this, client);   // 🔥 Виклик стану!
            }

        } catch (IOException e) {
            System.out.println("Error while running server: " + e.getMessage());
        }
    }

    public void handleClient(Socket socket) {
        try {
            internalMiddleware.invokeRequest(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setState(ServerState state) {
        this.state = state;
    }


    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void stop() {
        try {
            System.out.println("Stopping server...");
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            System.out.println("Server stopped.");
        } catch (IOException e) {
            System.out.println("Error stopping server: " + e.getMessage());
        }
    }


}
