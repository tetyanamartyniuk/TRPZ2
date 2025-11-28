package src.main.routes.adminRoutes;

import src.main.http.HttpRequest;
import src.main.http.HttpResponse;
import src.main.http.HttpResponseDirector;
import src.main.http.HttpRoute;

import java.util.HashMap;
import java.util.Map;
// package src.main.auth;
// ... інші імпорти

import src.main.auth.AuthService;
import src.main.auth.LoginFormParser;
import src.main.auth.SessionStore; // <-- Додати імпорт SessionStore

public class AdminLoginHandlerRoute implements HttpRoute {

    private final AuthService auth;

    public AdminLoginHandlerRoute(AuthService auth) {
        this.auth = auth;
    }

    @Override
    public HttpResponse execute(HttpRequest request) {
        Map<String, String> params = LoginFormParser.parse(request.getBody());

        String user = params.get("user");
        String pass = params.get("pass");

        System.out.println(user);
        System.out.println(pass);

        if (auth.authenticate(user, pass)) {
            Map<String, String> headers = new HashMap<>();
            String sessionToken = SessionStore.createSession();

            headers.put("Set-Cookie", "session=" + sessionToken + "; Path=/");

            return HttpResponseDirector.Redirect("/admin/stats", headers);
        }

        return HttpResponseDirector.Unauthorized("Wrong credentials", null);
    }
}


