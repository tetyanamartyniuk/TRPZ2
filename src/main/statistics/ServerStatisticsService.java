package src.main.statistics;

import src.main.database.DatabaseConnection;
import src.main.repos.StatisticsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ServerStatisticsService implements StatisticsRepository {

    private final DatabaseConnection db;

    public ServerStatisticsService(DatabaseConnection db) {
        this.db = db;
    }

    private long queryLong(Connection conn, String sql) throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
            return 0;
        }
    }

    @Override
    public void aggregateAndSaveStats() throws SQLException {
        try (Connection conn = db.getConnection()) {

            long totalRequests = queryLong(conn, "SELECT COUNT(*) FROM requests");
            long avgTime = queryLong(conn, "SELECT COALESCE(AVG(response_time_ms), 0) FROM logs");

            long successfulCount = queryLong(conn, "SELECT COUNT(*) FROM logs WHERE response_status LIKE '2%' OR response_status LIKE '3%'");
            long clientErrorCount = queryLong(conn, "SELECT COUNT(*) FROM logs WHERE response_status LIKE '4%'");
            long serverErrorCount = queryLong(conn, "SELECT COUNT(*) FROM logs WHERE response_status LIKE '5%'");

            String insertSql = "INSERT INTO server_metrics (" +
                    "created_at, total_requests_count, successful_requests_count, client_error_count, server_error_count, average_response_time_ms) " +
                    "VALUES (CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setLong(1, totalRequests);
                ps.setLong(2, successfulCount);
                ps.setLong(3, clientErrorCount);
                ps.setLong(4, serverErrorCount);
                ps.setLong(5, avgTime);
                ps.executeUpdate();
            }
        }
    }

    @Override
    public ServerStats getStats() {
        String selectSql = "SELECT total_requests_count, average_response_time_ms, successful_requests_count," +
                "client_error_count, server_error_count " +
                "FROM server_metrics ORDER BY created_at DESC LIMIT 1";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSql)) {

            if (rs.next()) {
                long totalRequests = rs.getLong(1);
                long avgTime = rs.getLong(2);
                long successfulRequests = rs.getLong(3);
                long clientErrors = rs.getLong(4);
                long serverErrors = rs.getLong(5);

                return new ServerStats(totalRequests, avgTime, successfulRequests, clientErrors, serverErrors);
            }
            return new ServerStats(0, 0, 0, 0, 0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toHtml() {
        ServerStats s = getStats();
        return String.format(
                "<html><body>" +
                        "<h1>Server Stats</h1>" +
                        "<p>Total requests: %d</p>" +
                        "<p>Avg time: %d ms</p>" +
                        "<p>Successful requests: %s</p>" +
                        "<p>Client errors: %s</p>" +
                        "<p>Server errors: %s</p>" +
                        "</body></html>",
                s.getTotalRequests(),
                s.getAvgResponseTimeMs(),
                s.getSuccessfulRequests(),
                s.getClientErrors(),
                s.getServerErrors()
        );
    }
}