package com.lerkin.titllist.dao.status;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.dao.entity.Status;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository

public class StatusDaoImpl implements StatusDao {

	private static final String SELECT_STATUSES = "SELECT name FROM statuses";

	@Override
	public List<Status> selectStatuses() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_STATUSES);
			resultSet = preparedStatement.executeQuery();
			return StatusParser.listParser(resultSet);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}
	}
}
