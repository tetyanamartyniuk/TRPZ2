package src.main.routes.adminRoutes;

import src.main.http.HttpRequest;
import src.main.http.HttpResponse;
import src.main.http.HttpResponseDirector;
import src.main.http.HttpRoute;

public class AdminLoginRoute implements HttpRoute {
    @Override
    public HttpResponse execute(HttpRequest request) {

        String html =
                "<html>" +
                        "<body>" +
                        "<h2>Admin Login</h2>" +
                        "<form method=\"post\" action=\"/admin/authenticate\">" +
                        "<input name=\"user\" placeholder=\"Username\"/> <br/>" +
                        "<input name=\"pass\" type=\"password\" placeholder=\"Password\"/> <br/>" +
                        "<button type=\"submit\">Login</button>" +
                        "</form>" +
                        "</body>" +
                        "</html>";

        return HttpResponseDirector.Ok(html, null);
    }
}
