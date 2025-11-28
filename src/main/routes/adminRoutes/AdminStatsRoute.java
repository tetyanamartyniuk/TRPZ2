package src.main.routes.adminRoutes;

import src.main.*;
import src.main.auth.AuthUtils;
import src.main.auth.SessionStore;
import src.main.repos.StatisticsRepository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

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
            // Оновлюємо таблицю server_metrics, читаючи сирі логи
            stats.aggregateAndSaveStats();
            logger.info("Statistics aggregated on demand for admin user.");

        } catch (SQLException e) {
            // Якщо агрегація не вдалася (наприклад, проблеми з підключенням до БД)
            logger.severe("Database error during on-demand statistics aggregation: " + e.getMessage());

            // Повертаємо помилку сервера (500)
            return HttpResponseDirector.InternalServerError(
                    "Internal Server Error: Failed to generate fresh statistics from logs.",
                    Collections.singletonMap("Content-Type", "text/plain"));
        }

        return HttpResponseDirector.Ok(stats.toHtml(), null);
    }
}
