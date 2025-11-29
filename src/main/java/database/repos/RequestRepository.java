package database.repos;

import http.HttpRequest;
import java.sql.SQLException;

public interface RequestRepository {
    int save(HttpRequest request, Integer bodyId) throws SQLException;
}
