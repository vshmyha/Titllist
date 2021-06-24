package com.lerkin.titllist.dao.role;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.entity_db.Role;
import com.lerkin.titllist.entity_db.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private static final String SELECT_ROLES_FOR_ADMIN = "SELECT role_name FROM roles WHERE role_name <> 'Admin' AND role_name <> 'Super Admin'";
    private static final String SELECT_ROLES_FOR_SUPER_ADMIN = "SELECT role_name FROM roles WHERE role_name <> 'Super Admin'";


    @Override
    public List<Role> selectRolesForAdmin() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Role> roles = new ArrayList<>();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ROLES_FOR_ADMIN);
            resultSet = preparedStatement.executeQuery();
            return RoleParser.listParser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Role> selectRolesForSuperAdmin() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Role> roles = new ArrayList<>();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ROLES_FOR_SUPER_ADMIN);
            resultSet = preparedStatement.executeQuery();
            return RoleParser.listParser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
    }
}
