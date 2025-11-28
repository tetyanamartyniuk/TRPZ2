package src.main;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseDirector {

    private static final HttpResponseBuilder builder = new HttpResponseBuilder();

    private static void setContentHeaders(String body, Map<String, String> headers) {
        if (body != null) {
            byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);
            headers.putIfAbsent("Content-Type", "text/html; charset=UTF-8");
            headers.put("Content-Length", String.valueOf(bodyBytes.length));
        } else {
            headers.put("Content-Length", "0");
        }
    }

    public static HttpResponse Ok(String body, Map<String, String> headers) {
        if (headers == null)
            headers = new HashMap<>();

        setContentHeaders(body, headers);

        builder.buildStatus("200 OK");
        builder.buildHeaders(headers);
        builder.buildBody(body);

        return builder.build();
    }


    public static HttpResponse NotFound(Map<String, String> headers) {
        if (headers == null)
            headers = new HashMap<>();

        setContentHeaders(null, headers);

        builder.buildStatus("404 Not Found");
        builder.buildHeaders(headers);
        builder.buildBody(null);
        return builder.build();
    }

    public static HttpResponse InternalServerError(String body, Map<String, String> headers) { // 🟢 ВИПРАВЛЕНО: Додано body
        if (headers == null)
            headers = new HashMap<>();

        setContentHeaders(body, headers);

        builder.buildStatus("500 Internal Server Error");
        builder.buildHeaders(headers);
        builder.buildBody(body);
        return builder.build();
    }

    public static HttpResponse BadRequest(Map<String, String> headers) {
        if (headers == null)
            headers = new HashMap<>();

        setContentHeaders(null, headers);

        builder.buildStatus("400 Bad Request");
        builder.buildHeaders(headers);
        builder.buildBody(null);
        return builder.build();
    }

    public static HttpResponse Created(String body, Map<String, String> headers) {
        if (headers == null)
            headers = new HashMap<>();

        setContentHeaders(body, headers);

        builder.buildStatus("201 Created");
        builder.buildHeaders(headers);
        builder.buildBody(body);
        return builder.build();
    }

    public static HttpResponse NoContent(Map<String, String> headers) {
        if (headers == null)
            headers = new HashMap<>();

        setContentHeaders(null, headers);

        builder.buildStatus("204 No Content");
        builder.buildHeaders(headers);
        builder.buildBody(null);
        return builder.build();
    }

    public static HttpResponse Forbidden(Map<String, String> headers) {
        if (headers == null)
            headers = new HashMap<>();

        setContentHeaders(null, headers);

        builder.buildStatus("403 Forbidden");
        builder.buildHeaders(headers);
        builder.buildBody(null);
        return builder.build();
    }

    public static HttpResponse Unauthorized(String body, Map<String, String> headers) {
        if (headers == null) {
            headers = new HashMap<>();
        }

        setContentHeaders(body, headers);

        builder.buildStatus("401 Unauthorized");
        builder.buildHeaders(headers);
        builder.buildBody(body);

        return builder.build();
    }

    public static HttpResponse ServiceUnavailable(String body, Map<String,String> headers) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        setContentHeaders(body, headers);
        builder.buildStatus("503 Service Unavailable");
        builder.buildHeaders(headers);
        builder.buildBody(body);
        return builder.build();
    }

    public static HttpResponse Redirect(String location, Map<String,String> headers) {
        String body = "<html><body>Redirecting to " + location + "...</body></html>";

        if (headers == null) {
            headers = new HashMap<>();
        }
        setContentHeaders(body, headers);
        builder.buildStatus("302 Found");
        headers.put("Location", location);
        builder.buildHeaders(headers);
        builder.buildBody(body);

        return builder.build();
    }
}