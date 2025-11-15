package src.main;

public class ServerManagment {
    public static void main(String[] args) {

        Server server = new Server(8083);
        server.start();
        sleep(1000);
        WebClient client = new WebClient();
        client.connect(server);
        client.sendRequest("localhost", 8083);
        sleep(2000);
        server.stop();
        client.sendRequest("localhost", 8083);
        sleep(500);
        server.start();
        sleep(1000);
        client.sendRequest("localhost", 8083);
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
