package src.main.auth;

public class AuthUtils {

    public static String extractSession(String cookieHeader) {
        if (cookieHeader == null) return null;

        for (String c : cookieHeader.split(";")) {
            String[] pair = c.trim().split("=");
            if (pair[0].equals("session")) return pair[1];
        }
        return null;
    }
}
