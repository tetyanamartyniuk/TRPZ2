package src.main.routes;

import src.main.http.HttpRequest;
import src.main.http.HttpResponse;
import src.main.http.HttpResponseDirector;
import src.main.http.HttpRoute;

import java.util.HashMap;
import java.util.Map;

import src.main.http.FileHandler;

import java.io.IOException;


public class HelloRoute implements HttpRoute {

    private static final String HTML_FILE_PATH = "src/main/resources/hello.html";

    private final FileHandler fileHandler;

    public HelloRoute(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public HttpResponse execute(HttpRequest request){
        try {
            String htmlContent = fileHandler.readFile(HTML_FILE_PATH);

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "text/html; charset=UTF-8");

            return HttpResponseDirector.Ok(htmlContent, headers);

        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponseDirector.InternalServerError("Failed to load: " + HTML_FILE_PATH, null);
        }
    }
}
