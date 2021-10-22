package com.lerkin.titllist.dao.anime;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity.Genre;
import com.lerkin.titllist.dto.Status;
import com.lerkin.titllist.dao.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository

public class AnimeDaoImpl implements AnimeDao {

	private static final String SELECT_ANIME_BY_GENRE = "SELECT a.id, a.rus_name, a.jap_name FROM anime_base AS a JOIN anime_genre ag ON a.id = ag.id_anime WHERE id_genre=?";
	private static final String SELECT_ANIME_BY_TYPE = "SELECT id, rus_name, jap_name FROM anime_base WHERE type_id = ?";
	private static final String SELECT_ANIME_BY_RELEASE_DATE = "SELECT id, rus_name, jap_name FROM anime_base WHERE release_date = ?";
	private static final String SELECT_ALL_ANIME = "SELECT id, rus_name, jap_name FROM anime_base";
	private static final String SELECT_ANIME_BY_ID = "SELECT abase.id, abase.rus_name, abase.jap_name, abase.episodes, abase.duration, abase.release_date, t.name\n" +
			"            FROM anime_base abase LEFT JOIN types t ON abase.type_id = t.id WHERE abase.id = ?";
	private static final String INSERT_NEW_ANIME = "INSERT anime_base(rus_name, jap_name, type_id, episodes, duration, release_date) VALUE (?, ?, ?, ?, ?, ?)";
	private static final String INSERT_ANIME_GENRE = "INSERT anime_genre(id_anime, id_genre) VALUE(?, ?)";
	private static final String INSERT_ANIME_TO_USER_TITLLIST = "INSERT INTO anime_user(id_user, id_anime, status) VALUES ((SELECT id FROM users WHERE username=?), ?, (SELECT id FROM statuses WHERE status=?))";
	private static final String SELECT_ANIME_FROM_USER_TITLLIST = "SELECT ab.id, ab.rus_name, ab.jap_name FROM anime_base ab JOIN anime_user au ON ab.id = au.id_anime WHERE au.id_user=?";
	private static final String SELECT_ANIME_BY_NAME = "SELECT id, rus_name, jap_name FROM anime_base WHERE rus_name LIKE ? OR jap_name LIKE ?";
	private static final String SELECT_ANIME_FROM_USER_TITLLIST_BY_STATUS = "SELECT ab.id, ab.rus_name, ab.jap_name\n" +
			"FROM anime_base ab\n" +
			"         JOIN anime_user au ON ab.id = au.id_anime\n" +
			"WHERE au.id_user = ?\n" +
			"  AND au.status = (SELECT id FROM statuses WHERE status = ?)";

	@Override
	public List<Anime> selectAnimeByGenre(Integer idGenre) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Anime> anime;
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_ANIME_BY_GENRE);
			preparedStatement.setInt(1, idGenre);
			resultSet = preparedStatement.executeQuery();
			anime = AnimeParser.listParse(resultSet);
			return anime;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public List<Anime> selectAnimeByTypes(Integer idType) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Anime> animes;
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_ANIME_BY_TYPE);
			preparedStatement.setInt(1, idType);
			resultSet = preparedStatement.executeQuery();
			animes = AnimeParser.listParse(resultSet);
			return animes;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}

	}

	@Override
	public List<Anime> selectAnimeByReleaseDate(Integer releaseDate) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Anime> animes;
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_ANIME_BY_RELEASE_DATE);
			preparedStatement.setInt(1, releaseDate);
			resultSet = preparedStatement.executeQuery();
			animes = AnimeParser.listParse(resultSet);
			return animes;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public List<Anime> selectAllAnime() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Anime> animes;
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_ANIME);
			resultSet = preparedStatement.executeQuery();
			animes = AnimeParser.listParse(resultSet);
			return animes;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}

	}

	@Override
	public void insertNewAnime(Anime anime) {

		String rusName = anime.getRusName();
		String japName = anime.getJapName();
		Integer typeId = anime.getType().getId();
		Integer episodesCount = anime.getEpisodes();
		Integer duration = anime.getDuration();
		Short releaseDate = anime.getReleaseDate();
		List<Genre> genres = anime.getGenres();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(INSERT_NEW_ANIME, preparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, rusName);
			preparedStatement.setString(2, japName);
			preparedStatement.setInt(3, typeId);
			preparedStatement.setInt(4, episodesCount);
			preparedStatement.setInt(5, duration);
			preparedStatement.setInt(6, releaseDate);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			int animeId = 0;
			if (resultSet.next()) {
				animeId = resultSet.getInt(1);
			}
			for (Genre genre : genres) {
				int genreId = genre.getId();
				preparedStatement = connection.prepareStatement(INSERT_ANIME_GENRE);
				preparedStatement.setInt(1, animeId);
				preparedStatement.setInt(2, genreId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}

	}

	@Override
	public Anime selectAnimeById(Integer animeId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Anime anime;
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_ANIME_BY_ID);
			preparedStatement.setInt(1, animeId);
			resultSet = preparedStatement.executeQuery();
			anime = AnimeParser.parse(resultSet);
			return anime;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public void insertAnimeToUserTitllist(User user, Integer animeId, Status status) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String statusName = status.getStatusName();
		String userName = user.getUserName();
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(INSERT_ANIME_TO_USER_TITLLIST);
			preparedStatement.setString(1, userName);
			preparedStatement.setInt(2, animeId);
			preparedStatement.setString(3, statusName);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement);
		}
	}

	@Override
	public List<Anime> selectAnimeFromUserTitllist(User user) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer userId = user.getId();
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_ANIME_FROM_USER_TITLLIST);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			List<Anime> animes = AnimeParser.listParse(resultSet);
			return animes;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public List<Anime> selectAnimeFromUserTitllistByStatus(User user, Status status) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer userId = user.getId();
		String statusName = status.getStatusName();
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_ANIME_FROM_USER_TITLLIST_BY_STATUS);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, statusName);
			resultSet = preparedStatement.executeQuery();
			List<Anime> animes = AnimeParser.listParse(resultSet);
			return animes;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public List<Anime> selectAnimesByName(String animeName) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_ANIME_BY_NAME);
			preparedStatement.setString(1, "%" + animeName + "%");
			preparedStatement.setString(2, "%" + animeName + "%");
			resultSet = preparedStatement.executeQuery();
			List<Anime> animes = AnimeParser.listParse(resultSet);
			return animes;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			ConnectionManager.close(connection, preparedStatement, resultSet);
		}
	}
}
