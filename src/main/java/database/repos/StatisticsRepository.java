package database.repos;

import statistics.ServerStats;

import java.sql.SQLException;

public interface StatisticsRepository {

    void aggregateAndSaveStats() throws SQLException;

    ServerStats getStats();

    String toHtml();
}