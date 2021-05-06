package com.lerkin.titllist.dao.query.genre;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.dao.query.anime.AnimeParser;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl implements GenreDao {

    private static final String SELECT_ALL_GENRE = "SELECT id_genre, genre FROM genres ORDER BY genre";
    private static final String SELECT_GENRES_BY_ANIME_ID = "SELECT g.id_genre, g.genre FROM anime_base AS a  JOIN anime_genre ag ON a.id_anime = ag.id_anime JOIN genres g ON ag.id_genre = g.id_genre WHERE ag.id_anime = ?";
    private static final String SELECT_ANIME_BY_GENRE = "SELECT a.id_anime, a.rus_name, a.jap_name, a.episods, a.duration, a.release_date FROM anime_base AS a JOIN anime_genre ag ON a.id_anime = ag.id_anime WHERE id_genre=?";


    @Override
    public List<Genre> selectGenres() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Genre> genres = new ArrayList<>();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_GENRE);
            resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (next) {
                genres = GenreParser.parse(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
        return genres;
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
            boolean next = resultSet.next();
            if (next) {
                genres = GenreParser.parse(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
        return genres;
    }

    @Override
    public List<Anime> selectByGenre(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Anime> anime = new ArrayList<>();
        try{
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ANIME_BY_GENRE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (next) {
                anime = AnimeParser.parse(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
        return anime;
    }
}
