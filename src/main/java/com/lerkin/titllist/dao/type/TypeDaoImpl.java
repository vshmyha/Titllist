package com.lerkin.titllist.dao.type;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.dao.entity.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TypeDaoImpl implements TypeDao {

    private static final String SELECT_ALL_TYPE = "SELECT id_type, type_name FROM type";
    private static final String SELECT_TYPE_BY_ANIME_ID = "SELECT  id_type, type_name FROM type t JOIN anime_base a ON a.type_id = t.id_type WHERE a.id_anime =?";

    @Override
    public List<Type> selectTypes() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_TYPE);
            resultSet = preparedStatement.executeQuery();
            return TypeParser.parseListTypes(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Type selectTypeByAnimeId(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Type type = null;
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_TYPE_BY_ANIME_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                type = TypeParser.parse(resultSet);
            }
            return type;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }

    }

}
