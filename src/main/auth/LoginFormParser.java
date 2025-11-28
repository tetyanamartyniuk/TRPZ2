package src.main.auth;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginFormParser {

    public static Map<String, String> parse(String body) {
        Map<String, String> result = new HashMap<>();

        if (body == null || body.isEmpty()) return result;

        String[] pairs = body.split("&");

        for (String pair : pairs) {
            String[] kv = pair.split("=", 2);

            String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
            String value = kv.length > 1
                    ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8)
                    : "";

            result.put(key, value);
        }
        return result;
    }
}

