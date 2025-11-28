package src.main.database.repos;

import src.main.http.HttpRequest;
import java.sql.SQLException;

public interface RequestRepository {
    int save(HttpRequest request, Integer bodyId) throws SQLException;
}
