package routes;


import org.thymeleaf.context.Context;
import http.*;

public class DynamicRoute implements HttpRoute {
        private final ThymeleafRenderer renderer = new ThymeleafRenderer();

        @Override
        public HttpResponse execute(HttpRequest request) {

            Context ctx = new Context();
            ctx.setVariable("name", request.getQueryParams().getOrDefault("name",
                    "Guest"));
            ctx.setVariable("path", request.getPath());
            ctx.setVariable("now", java.time.LocalDateTime.now());

            String html = renderer.render("dynamic", ctx);

            HttpResponse response = HttpResponseDirector.Ok(html, request.getHeaders());
            return response;
        }
    }


