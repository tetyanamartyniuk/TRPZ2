package src.main;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
public class HttpResponseDirector {

    private static final HttpResponseBuilder builder = new HttpResponseBuilder();

    public static HttpResponse Ok(String body, Map<String, String> headers) {

        if (headers == null)
            headers = new HashMap<>();

        byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);

        headers.put("Content-Type", "text/plain; charset=UTF-8");
        headers.put("Content-Length", String.valueOf(bodyBytes.length));

        builder.buildStatus("200 OK");
        builder.buildHeaders(headers);
        builder.buildBody(body);

        return builder.build();
    }


    public static HttpResponse NotFound(Map<String, String> headers) {
        builder.buildStatus("404 Not Found");
        builder.buildHeaders(headers);
        builder.buildBody(null);
        return builder.build();
    }

    public static HttpResponse InternalServerError(Map<String, String> headers) {
        builder.buildStatus("500 Internal Server Error");
        builder.buildHeaders(headers);
        builder.buildBody(null);
        return builder.build();
    }

    public static HttpResponse BadRequest(Map<String, String> headers) {
        builder.buildStatus("400 Bad Request");
        builder.buildHeaders(headers);
        builder.buildBody(null);
        return builder.build();
    }

    public static HttpResponse Created(String body, Map<String, String> headers) {
        builder.buildStatus("201 Created");
        builder.buildHeaders(headers);
        builder.buildBody(body);
        return builder.build();
    }

    public static HttpResponse NoContent(Map<String, String> headers) {
        builder.buildStatus("204 No Content");
        builder.buildHeaders(headers);
        builder.buildBody(null);
        return builder.build();
    }

    public static HttpResponse Forbidden(Map<String, String> headers) {
        builder.buildStatus("403 Forbidden");
        builder.buildHeaders(headers);
        builder.buildBody(null);
        return builder.build();
    }
    public static HttpResponse ServiceUnavailable(String body, Map<String,String> headers) {
        builder.buildStatus("503 Service Unavailable");
        builder.buildHeaders(headers);
        builder.buildBody(body);
        return builder.build();
    }
}
