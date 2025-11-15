package src.main;


import java.net.Socket;

public interface ServerState {
    void start(Server server);
    void stop(Server server);
    void handleClient(Server server, Socket client);
}

