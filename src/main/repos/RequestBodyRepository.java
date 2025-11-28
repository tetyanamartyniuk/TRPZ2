package src.main.repos;

import src.main.DatabaseConnection;

import java.sql.*;

public class RequestBodyRepository {

    private final DatabaseConnection db;

    public RequestBodyRepository(DatabaseConnection db) {
        this.db = db;
    }

    public int saveBody(String body) throws SQLException {
        String sql =
            "INSERT INTO request_body(content)" +
            "VALUES (?)" +
            "RETURNING id";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, body);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }
}
