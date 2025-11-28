package src.main.repos;

import src.main.database.DatabaseConnection;

import java.sql.*;

public class LogRepository {

    private final DatabaseConnection db;

    public LogRepository(DatabaseConnection db) {
        this.db = db;
    }

    public void log(int requestId, int status, long durationMs) throws SQLException {
        String sql =
            "INSERT INTO logs(request_id, response_status, response_time_ms)" +
            "VALUES (?, ?, ?);";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, requestId);
            ps.setString(2, String.valueOf(status));
            ps.setLong(3, durationMs);

            ps.executeUpdate();
        }
    }
}
