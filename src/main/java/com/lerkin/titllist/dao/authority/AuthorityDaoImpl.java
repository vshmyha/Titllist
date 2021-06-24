package com.lerkin.titllist.dao.authority;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.dao.role.RoleParser;
import com.lerkin.titllist.entity_db.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorityDaoImpl implements AuthorityDao {
    private static final String SELECT_ROLE_QUERY = "SELECT r.role_name FROM users AS u LEFT JOIN roles AS r ON u.role_id = r.id WHERE u.id=?";

    @Override
    public Role userRole(Integer userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Role role = null;
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ROLE_QUERY);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               role = RoleParser.parse(resultSet);
            }
            return role;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
    }
}
