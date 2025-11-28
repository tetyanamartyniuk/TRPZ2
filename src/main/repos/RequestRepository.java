package src.main.repos;

import src.main.HttpRequest;
import java.sql.SQLException;

public interface RequestRepository {
    int save(HttpRequest request, Integer bodyId) throws SQLException;
}
