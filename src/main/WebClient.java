package src.main;

import java.io.*;
import java.net.Socket;

public class WebClient implements ClientInterface {
    private Server connectedServer;

    @Override
    public void connect(Server server) {
        this.connectedServer = server;
        System.out.println("Client connected to server (logical connection)");
    }

    public void sendRequest(String host, int port) {
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("TCP connection established.");

            out.println("GET / HTTP/1.1");
            out.println("Host: " + host);
            out.println();
            out.flush();

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
                if (line.isEmpty()) break;  // кінець хедерів
            }

            while (in.ready()) {
                response.append((char) in.read());
            }

            System.out.println("----- SERVER RESPONSE -----");
            System.out.println(response);

        } catch (Exception e) {
            System.out.println("Client: " + e.getMessage());
        }

        System.out.println("Client connection closed.");
    }

}
