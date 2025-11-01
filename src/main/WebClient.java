package src.main;

 public class WebClient implements ClientInterface {
    private HttpServer connectedServer;

    @Override
    public void connect(HttpServer server) {
        this.connectedServer = server;
        System.out.println("Client connected to server");
    }

    @Override
    public HttpResponse sendRequest(HttpRequest request) {
        if (connectedServer == null) {
            return new HttpResponse(503, "No server connected");
        }
        return connectedServer.handleRequest(request);
    }
}
