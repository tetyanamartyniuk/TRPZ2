package src.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;

public abstract class Middleware {
    public abstract Handler createHandler();
    public void invokeRequest(Socket socket) throws IOException,
            SQLException {
        long requestTime = System.currentTimeMillis();
        HttpRequest request = parseRequest(socket.getInputStream());
        Handler handler = createHandler();
        HttpResponse response = handleRequest(handler,request);
        long responseTime = System.currentTimeMillis();
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
    public void saveStatistic(){
    }
}