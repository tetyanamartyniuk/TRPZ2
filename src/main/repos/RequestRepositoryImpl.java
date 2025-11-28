package src.main.repos;

import org.json.JSONObject;
import src.main.DatabaseConnection;
import src.main.HttpRequest;
import src.main.repos.RequestRepository;

import java.sql.*;

public class RequestRepositoryImpl implements RequestRepository {

    private final DatabaseConnection db;

    public RequestRepositoryImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public int save(HttpRequest request, Integer bodyId) throws SQLException {
        String sql =
            "INSERT INTO requests(method, url, headers, body_id)" +
            "VALUES (?, ?, ?::jsonb, ?)" +
            "RETURNING id";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, request.getMethod());
            ps.setString(2, request.getPath());

            JSONObject headersJson = new JSONObject(request.getHeaders());
            ps.setString(3, headersJson.toString());

            if (bodyId != null)
                ps.setInt(4, bodyId);
            else
                ps.setNull(4, Types.INTEGER);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }
}

