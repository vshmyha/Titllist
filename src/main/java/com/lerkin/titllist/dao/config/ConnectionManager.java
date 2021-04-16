package com.lerkin.titllist.dao.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private static final ConnectionPool CONNECTION_POOL = new ConnectionPool();

    public static Connection takeConnection() {
        try {
            return CONNECTION_POOL.takeConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException("Connection taking fail", e);
        }
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public static void close(Connection connection, Statement statement) throws SQLException {
        close(connection, statement, null);
    }

    public static void close(Connection connection) throws SQLException {
        close(connection, null, null);
    }
}
