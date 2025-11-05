package src.main;

public class RunningServer extends ServerState{

    public RunningServer(HttpServer server){
        super(server);
    }

    public void start(){
        System.out.println("Сервер уже запущений");
    }

    public void stop(){
        System.out.println("Зупиняємо сервер");
        server.setState(new StoppedServer(server));
    }

    public void handleRequest() {
        System.out.println("Обробляємо запити");
    }
}
