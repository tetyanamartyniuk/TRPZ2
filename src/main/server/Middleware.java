package src.main.server;

import src.main.http.HttpRequest;
import src.main.http.HttpRequestParser;
import src.main.http.HttpResponse;
import src.main.repos.LogRepository;
import src.main.repos.RequestBodyRepository;
import src.main.repos.RequestRepository;
import src.main.repos.StatisticsRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;

public abstract class Middleware {

    protected final RequestRepository requestRepository;
    protected final RequestBodyRepository requestBodyRepository;
    protected final LogRepository logsRepository;

    protected final StatisticsRepository statisticsRepository;

    public Middleware(RequestRepository requestRepository,
                      RequestBodyRepository requestBodyRepository,
                      LogRepository logsRepository,
                      StatisticsRepository statisticsRepository) {

        this.requestRepository = requestRepository;
        this.requestBodyRepository = requestBodyRepository;
        this.logsRepository = logsRepository;
        this.statisticsRepository = statisticsRepository;
    }
    public abstract Handler createHandler();


    public void invokeRequest(Socket socket) throws IOException, SQLException {

        long requestStart = System.currentTimeMillis();

        HttpRequest request = parseRequest(socket.getInputStream());
        Handler handler = createHandler();
        HttpResponse response = handleRequest(handler, request);

        long requestEnd = System.currentTimeMillis();
        long duration = requestEnd - requestStart;

        Integer bodyId = null;
        if (request.getBody() != null && !request.getBody().isEmpty()) {
            bodyId = requestBodyRepository.saveBody(request.getBody());
        }
        int requestId = requestRepository.save(request, bodyId);
        logsRepository.log(requestId, response.getStatusCode(), duration);
        sendResponse(response, socket.getOutputStream());

        socket.close();
    }

    public HttpResponse handleRequest(Handler handler, HttpRequest
            request){
        return handler.handle(request);
    }
    public HttpRequest parseRequest(InputStream inputStream) throws
            IOException {
        HttpRequestParser httpRequestParser = new
                HttpRequestParser();
        return httpRequestParser.parse(inputStream);
    }
    public void sendResponse(HttpResponse response, OutputStream
            outputStream) throws IOException {
        response.send(outputStream);
    }

}