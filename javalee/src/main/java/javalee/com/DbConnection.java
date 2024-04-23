package javalee.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/RelatorioClima";
    private static final String DB_USER = "postgres"; 
    private static final String DB_PASSWORD = "1234"; 

    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                throw new SQLException("Error connecting to database: " + e.getMessage());
            }
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}