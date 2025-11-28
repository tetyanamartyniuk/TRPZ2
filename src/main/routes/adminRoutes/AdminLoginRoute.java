package src.main.routes.adminRoutes;

import src.main.HttpRequest;
import src.main.HttpResponse;
import src.main.HttpResponseDirector;
import src.main.HttpRoute;

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
