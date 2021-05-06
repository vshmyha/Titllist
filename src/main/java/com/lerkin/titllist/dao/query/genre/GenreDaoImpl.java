package com.lerkin.titllist.dao.query.genre;

import com.lerkin.titllist.dao.config.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl implements GenreDao {

    private static final String SELECT_ALL_GENRE = "SELECT genre FROM genres ORDER BY genre";

    @Override
    public List<String> selectGenres() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> types = new ArrayList<>();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_GENRE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String type = resultSet.getString(1);
                types.add(type);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
        return types;
    }
}
