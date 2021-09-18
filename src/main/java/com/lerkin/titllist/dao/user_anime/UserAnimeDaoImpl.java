package com.lerkin.titllist.dao.user_anime;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.entity.UserAnime;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository

public class UserAnimeDaoImpl implements UserAnimeDao {

	private static final String SELECT_ANIME_USER =
			"SELECT abase.id, auser.id_user, abase.rus_name, abase.jap_name, abase.episodes, abase.duration, abase.release_date, s.name, t.name\n" +
					"FROM anime_base abase\n" +
					"         LEFT JOIN anime_user auser ON abase.id = auser.id_anime\n" +
					"         LEFT JOIN statuses s ON auser.status = s.id\n" +
					"         LEFT JOIN types t ON abase.type_id = t.id\n" +
					"WHERE auser.id_anime = ?\n" +
					"  AND auser.id_user = ?";
	private static final String SELECT_CURRENT_ANIME_STATUS = "SELECT s.name FROM statuses s JOIN anime_user au ON s.id = au.status\n" +
			"WHERE au.id_user = ? AND au.id_anime = ?";
	private static final String UPDATE_CURRENT_ANIME_STATUS = "UPDATE anime_user SET status=(SELECT id FROM statuses WHERE statuses.name=?) WHERE id_anime=? AND id_user=?";

	@Override
	public UserAnime selectUserAnime(Integer animeId, Integer userId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_ANIME_USER);
			preparedStatement.setInt(1, animeId);
			preparedStatement.setInt(2, userId);
			resultSet = preparedStatement.executeQuery();
			UserAnime userAnime = UserAnimeParser.parser(resultSet);
			return userAnime;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public Status selectCurrentAnimeStatus(Integer animeId, Integer userId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Status status = null;
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_CURRENT_ANIME_STATUS);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, animeId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String statusName = resultSet.getString("status");
				status = Status.byText(statusName);
			}
			return status;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public void updateCurrentStatus(Integer animeId, Integer userId, Status status) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String statusName = status.getStatusName();
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(UPDATE_CURRENT_ANIME_STATUS);
			preparedStatement.setString(1, statusName);
			preparedStatement.setInt(2, animeId);
			preparedStatement.setInt(3, userId);
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement);
		}
	}
}
