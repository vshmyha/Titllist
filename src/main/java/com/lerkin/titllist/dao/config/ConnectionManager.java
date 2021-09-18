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

	public static void close(Connection connection, Statement statement, ResultSet resultSet) {

		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	public static void close(Connection connection, Statement statement) {

		close(connection, statement, null);
	}

	public static void close(Connection connection) {

		close(connection, null, null);
	}
}
