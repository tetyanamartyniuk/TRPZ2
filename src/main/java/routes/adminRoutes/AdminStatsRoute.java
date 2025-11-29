package routes.adminRoutes;

import auth.AuthUtils;
import auth.SessionStore;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpResponseDirector;
import http.HttpRoute;
import database.repos.StatisticsRepository;
import statistics.Logger;

import java.sql.SQLException;
import java.util.Collections;

public class AdminStatsRoute implements HttpRoute {

    private final StatisticsRepository stats;
    private final Logger logger = new Logger();

    public AdminStatsRoute(StatisticsRepository stats) {
        this.stats = stats;
    }

    @Override
    public HttpResponse execute(HttpRequest request) {

        String cookie = request.getHeader("Cookie");
        String token = AuthUtils.extractSession(cookie);

        if (!SessionStore.isValid(token)) {
            return HttpResponseDirector.Redirect("/admin/login", null);
        }
        try {
            stats.aggregateAndSaveStats();
            logger.info("Statistics aggregated on demand for admin user.");

        } catch (SQLException e) {
            logger.severe("Database error during on-demand statistics aggregation: " + e.getMessage());

            return HttpResponseDirector.InternalServerError(
                    "Internal Server Error: Failed to generate fresh statistics from logs.",
                    Collections.singletonMap("Content-Type", "text/plain"));
        }

        return HttpResponseDirector.Ok(stats.toHtml(), null);
    }
}
