package com.lerkin.titllist.dao.genre;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.dao.entity.Genre;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository

public class GenreDaoImpl implements GenreDao {

    private static final String SELECT_ALL_GENRE = "SELECT id, name FROM genres ORDER BY name";
    private static final String SELECT_GENRES_BY_ANIME_ID = "SELECT g.id, g.name FROM anime_base AS a  JOIN anime_genre ag ON a.id = ag.id_anime JOIN genres g ON ag.id_genre = g.id WHERE ag.id_anime = ?";

    @Override
    public List<Genre> selectGenres() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Genre> genres;
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_GENRE);
            resultSet = preparedStatement.executeQuery();
            genres = GenreParser.parse(resultSet);
            return genres;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Genre> selectGenresByAnimeId(Integer animeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Genre> genres = new ArrayList<>();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_GENRES_BY_ANIME_ID);
            preparedStatement.setInt(1, animeId);
            resultSet = preparedStatement.executeQuery();
            genres = GenreParser.parse(resultSet);
            return genres;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
    }
}
