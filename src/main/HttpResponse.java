package src.main;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

        private final String status;
        private final Map<String, String> headers;
        private final String body;

        public HttpResponse(String status, Map<String, String> headers, String body) {
            this.status = status;
            this.headers = headers;
            this.body = body;
        }
    }
