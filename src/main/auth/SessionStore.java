package src.main.auth;

import java.util.*;

public class SessionStore {
    private static final Map<String, Boolean> sessions = new HashMap<>();

    public static String createSession() {
        String token = UUID.randomUUID().toString();
        sessions.put(token, true);
        return token;
    }

    public static boolean isValid(String token) {
        return token != null && sessions.containsKey(token);
    }
}
