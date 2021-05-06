package com.lerkin.titllist.dao.query.type;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.dao.query.anime.AnimeParser;
import com.lerkin.titllist.dao.query.genre.GenreDao;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Type;
import com.lerkin.titllist.service.query.genre.GenreService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDaoImpl implements TypeDao {

    private static final String SELECT_ALL_TYPE = "SELECT id_type, type_name FROM type";
    private static final String SELECT_ANIME_BY_TYPE = "SELECT id_anime, rus_name, jap_name, episods, duration, release_date FROM anime_base WHERE type_id = ?";
    private static final String SELECT_TYPE_BY_ANIME_ID = "SELECT  id_type, type_name FROM type t JOIN anime_base a ON a.type_id = t.id_type WHERE a.id_anime =?";

    @Override
    public List<Type> selectTypes() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Type> types = new ArrayList<>();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_TYPE);
            resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (next) {
                types = TypeParser.parseListTypes(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
        return types;
    }

    @Override
    public List<Anime> selectByTypes(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Anime> animes = new ArrayList<>();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ANIME_BY_TYPE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (next) {
                animes = AnimeParser.parse(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
        return animes;
    }

    @Override
    public Type selectTypeByAnimeId(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Type type = new Type();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_TYPE_BY_ANIME_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (next) {
                type = TypeParser.parse(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
        return type;
    }

}
