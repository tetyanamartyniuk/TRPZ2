package http;

import statistics.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestParser {
    private final Logger logger = new Logger();

    public HttpRequest parse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();

        String[] requestLineParts = line.split(" ");
        String method = requestLineParts[0];
        String path = requestLineParts[1];
        String version = requestLineParts[2];

        Map<String, String> headers = new HashMap<>();
        int contentLength = -1;

        while (!(line = reader.readLine()).isEmpty()) {
            String[] headerParts = line.split(": ");
            headers.put(headerParts[0], headerParts[1]);

            if (headerParts[0].equalsIgnoreCase("Content-Length")) {
                contentLength = Integer.parseInt(headerParts[1].trim());
            }
        }

        StringBuilder body = new StringBuilder();
        if (contentLength != -1) {
            char[] bodyBuffer = new char[contentLength];
            int bytesRead = 0;
            while (bytesRead < contentLength) {
                int byteData = reader.read();
                if (byteData == -1) {
                    throw new IOException("End of stream reached before reading full Content-Length");
                }
                bodyBuffer[bytesRead++] = (char) byteData;
            }
            body.append(bodyBuffer);
        }

        Map<String, String> queryParams = parseQueryParams(path);

        logger.info("Parsed request: " + method + " " + path + " " +
                version + " " + headers + " " + body);

        return new HttpRequest(method, path, headers, body.toString(), queryParams);
    }

    private Map<String, String> parseQueryParams(String path) {
        Map<String, String> queryParams = new HashMap<>();

        if (path == null) return queryParams;

        int questionIndex = path.indexOf('?');
        if (questionIndex == -1) return queryParams;

        String queryString = path.substring(questionIndex + 1);
        if (queryString.isEmpty()) return queryParams;

        String[] pairs = queryString.split("&");

        for (String pair : pairs) {
            String[] kv = pair.split("=", 2);
            String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
            String value = kv.length > 1
                    ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8)
                    : "";
            queryParams.put(key, value);
        }
        return queryParams;
    }
}
