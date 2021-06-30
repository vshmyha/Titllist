package com.lerkin.titllist.dao.anime;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnimeDaoImpl implements AnimeDao {

    private static final String SELECT_ANIME_BY_GENRE = "SELECT a.id_anime, a.rus_name, a.jap_name, a.episods, a.duration, a.release_date FROM anime_base AS a JOIN anime_genre ag ON a.id_anime = ag.id_anime WHERE id_genre=?";
    private static final String SELECT_ANIME_BY_TYPE = "SELECT id_anime, rus_name, jap_name, episods, duration, release_date FROM anime_base WHERE type_id = ?";
    private static final String SELECT_ANIME_BY_RELEASE_DATE = "SELECT id_anime, rus_name, jap_name, episods, duration, release_date FROM anime_base WHERE release_date = ?";
    private static final String SELECT_ALL_ANIME = "SELECT id_anime, rus_name, jap_name FROM anime_base";
    private static final String SELECT_ANIME_BY_ID = "SELECT id_anime, rus_name, jap_name, episods, duration, release_date FROM anime_base WHERE id_anime = ?";
    private static final String INSERT_NEW_ANIME = "INSERT anime_base(rus_name, jap_name, type_id, episods, duration, release_date) VALUE (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ANIME_GENRE = "INSERT anime_genre(id_anime, id_genre) VALUE(?, ?)";

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
        Integer episodesCount = anime.getEpisodesCount();
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
            if(resultSet.next()){
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
            anime = AnimeParser.parser(resultSet);
            return anime;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
    }
}
