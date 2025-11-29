package routes.adminRoutes;

import http.HttpRequest;
import http.HttpResponse;
import http.HttpResponseDirector;
import http.HttpRoute;

import java.util.HashMap;
import java.util.Map;

import auth.AuthService;
import auth.LoginFormParser;
import auth.SessionStore;

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


