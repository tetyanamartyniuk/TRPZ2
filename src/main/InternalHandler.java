//package src.main;
//
//import java.io.FileNotFoundException;
//
//public class InternalHandler implements Handler {
//
//    private static final Logger logger = Logger.getGlobal();
//
//    private final FileHandler fileHandler = new FileHandler();
//    private final Map<String, HttpRequestCallback> routes;
//
//    public InternalHandler(Map<String, HttpRequestCallback> routes) {
//        this.routes = routes;
//    }
//
//    @Override
//    public HttpResponse handle(HttpRequest request) {
//        HttpResponse response;
//
//        logger.info("Handling request for path: " + request.getPath());
//        String path = request.getPath();
//
//        if (routes.containsKey(path)) {
//            HttpRoute callback = routes.get(path);
//            response = callback.execute(request);
//            logger.info("Route found for path: " + path);
//        } else {
//            if (path.equals("/")) {
//                path = "/index.html";
//            }
//
//            try {
//                String fileContent = fileHandler.readFile("src/main/resources" + path);
//                logger.info("File found for path: " + path);
//
//                response = HttpResponseDirector.Ok(
//                        fileContent,
//                        request.getHeaders()
//                );
//
//            } catch (FileNotFoundException e) {
//                logger.warning("File not found for path: " + path);
//
//                response = HttpResponseDirector.NotFound(
//                        request.getHeaders()
//                );
//            }
//        }
//
//        return response;
//    }
//}
//
