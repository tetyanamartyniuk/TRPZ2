package src.main;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private ServerState state;
    private boolean running;
    private int port;
    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
        this.state = new StoppedServer();
        this.running = false;
    }

    public void setState(ServerState state) {
        this.state = state;
    }

    public ServerState getState() {
        return state;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getPort() {
        return port;
    }

    // --- ВАЖЛИВО: делегуємо поведінку стану ---
    public void start() {
        state.start(this);
    }

    public void stop() {
        state.stop(this);
    }

    public void handleClient(Socket client) {
        state.handleClient(this, client);
    }


    // --- ЄДИНИЙ метод обробки клієнта ---
    public void processClient(Socket client) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             OutputStream output = client.getOutputStream()) {

            String requestLine = reader.readLine();
            System.out.println("Received request: " + requestLine);

            String body = "The first request`s body";

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "text/plain");
            headers.put("Content-Length", String.valueOf(body.getBytes().length));

            HttpResponse response = HttpResponseDirector.Ok(body, headers);

            output.write(response.toHttpString().getBytes());
            output.flush();

            System.out.println("Response sent.");

        } catch (IOException e) {
            System.out.println("Error processing client: " + e.getMessage());
        } finally {
            try {
                client.close();
                System.out.println("Client connection closed.");
            } catch (IOException ignored) {}
        }
    }

    // --- Доступ для станів ---
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket socket) {
        this.serverSocket = socket;
    }
}
