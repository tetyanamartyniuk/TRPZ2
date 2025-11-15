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


         public String toHttpString() {
            StringBuilder sb = new StringBuilder();
            sb.append("HTTP/1.1 ").append(status).append("\r\n");

            for (Map.Entry<String, String> header : headers.entrySet()) {
                sb.append(header.getKey()).append(": ").append(header.getValue()).append("\r\n");
            }

             sb.append("\r\n");

            if (body != null) {
                sb.append(body);
            }

            return sb.toString();
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public int getStatusCode() {
        if (status == null || status.isEmpty()) {
            return -1;
        }

        try {
            String[] parts = status.trim().split(" ");
            return Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
