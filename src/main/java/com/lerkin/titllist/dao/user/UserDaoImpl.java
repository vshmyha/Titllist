package com.lerkin.titllist.dao.user;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.entity_db.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM users WHERE username=? AND password=?";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username=?";
    private static final String INSERT_NEW_USER = "INSERT users (username, password, role_id) VALUES (?, ?, (SELECT id FROM roles WHERE role_name=?))";
    private static final String UPDATE_NEW_PASSWORD = "UPDATE users SET password = ? where username = ?";

    @Override
    public User selectUser(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (next) {
                user = UserParser.parse(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public boolean isUserExist(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isUserExist;
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            isUserExist = resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
        return isUserExist;
    }

    @Override
    public void addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(INSERT_NEW_USER);
            String userName = user.getUserName();
            preparedStatement.setString(1, userName);
            String password = user.getPassword();
            preparedStatement.setString(2, password);
            String role = user.getRole().getRoleName();
            preparedStatement.setString(3, role);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement);
        }
    }

    @Override
    public void changePassword(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String username = user.getUserName();
        String password = user.getPassword();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_NEW_PASSWORD);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement);
        }
    }
}
