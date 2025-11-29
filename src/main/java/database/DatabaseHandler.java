package database;

import statistics.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
    private final DatabaseConnection databaseConnection;

    private final Logger logger = new Logger();

    public DatabaseHandler(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void createTable() {
        String createRequestBodyTable =
            "CREATE TABLE IF NOT EXISTS request_body(" +
                    "id SERIAL PRIMARY KEY," +
                    "content TEXT" +
                    ");";

        String createRequestsTable =
                "CREATE TABLE IF NOT EXISTS requests (" +
                        "id SERIAL PRIMARY KEY, " +
                        "method VARCHAR(10) NOT NULL, " +
                        "url TEXT NOT NULL, " +
                        "headers JSONB, " +
                        "body_id INT REFERENCES request_body(id) ON DELETE CASCADE, " +
                        "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ");";

        String createLogsTable =
                "CREATE TABLE IF NOT EXISTS logs (" +
                        "id SERIAL PRIMARY KEY, " +
                        "request_id INT REFERENCES requests(id) ON DELETE CASCADE, " +
                        "response_status VARCHAR, " +
                        "response_time_ms INT, " +
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ");";

        String createStatsTable =
                "CREATE TABLE IF NOT EXISTS server_metrics (" +
                        "    id SERIAL PRIMARY KEY," +
                        "    created_at TIMESTAMP NOT NULL," +
                        "    total_requests_count INT NOT NULL DEFAULT 0," +
                        "    successful_requests_count INT NOT NULL DEFAULT 0," +
                        "    client_error_count INT NOT NULL DEFAULT 0," +
                        "    server_error_count INT NOT NULL DEFAULT 0," +
                        "    average_response_time_ms INT" +
                        ");";

        try (Connection conn = databaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createRequestBodyTable);
            stmt.execute(createRequestsTable);
            stmt.execute(createLogsTable);
            stmt.execute(createStatsTable);
            logger.info("Tables created successfully.");
        } catch (SQLException e) {
            logger.severe("Error creating table: " + e.getMessage()
            )
            ;

        }
    }
}



