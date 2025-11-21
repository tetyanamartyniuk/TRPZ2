package src.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class InternalHandler implements Handler {


    private final Logger logger = new Logger();

    private final FileHandler fileHandler = new FileHandler();

    RouteComponent routes;
    public InternalHandler(RouteComponent routes){
        this.routes=routes;
    }
    @Override
    public HttpResponse handle(HttpRequest request) {
        HttpResponse response;
        logger.info("Handling request for path: " +
                request.getPath());
        response = routes.handle(request);
        String path = request.getPath();
        if (response!=null) {
            logger.info("Route found for path: " + path);
        }else {
            if(path.equals("/")) {
                path = "/index.html";
            }
            try{
                String fileContent =
                        fileHandler.readFile("src/main/resources" + path);
                logger.info("File found for path: " + path);
                response = HttpResponseDirector.Ok(fileContent,
                        request.getHeaders());
            }
            catch (IOException fileNotFoundException) {
                logger.warning("File not found for path: " + path);
                response =
                        HttpResponseDirector.NotFound(request.getHeaders());
            }
        }
        return response;
    }
}

