package src.main.database.repos;

import src.main.statistics.ServerStats;

import java.sql.SQLException;

public interface StatisticsRepository {

    void aggregateAndSaveStats() throws SQLException;

    ServerStats getStats();

    String toHtml();
}