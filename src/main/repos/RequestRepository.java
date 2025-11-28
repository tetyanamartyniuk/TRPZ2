package src.main.repos;

import src.main.http.HttpRequest;
import java.sql.SQLException;

public interface RequestRepository {
    int save(HttpRequest request, Integer bodyId) throws SQLException;
}
