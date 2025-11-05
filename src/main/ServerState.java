package src.main;

abstract  class ServerState {

    protected HttpServer server;

    public ServerState(HttpServer server) {
        this.server = server;
    }

    public abstract void start();
    public abstract void stop();
    public abstract void handleRequest();

}
