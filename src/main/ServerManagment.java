package src.main;

public class ServerManagment {

    public static void main(String[] args){
        HttpServer server = new HttpServer();

        server.handleRequest();
        server.start();
        server.handleRequest();
        server.stop();
    }

}
