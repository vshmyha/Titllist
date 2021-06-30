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
    private static final String UPDATE_ROLE = "UPDATE users SET role_id = (SELECT id FROM roles WHERE role_name=?) WHERE id = ?";
    private static final String SELECT_AVAILABLE_ROLES = "SELECT role_name FROM roles JOIN available_roles ar ON roles.id = ar.available_role_id " +
            "WHERE ar.role_id = (SELECT id FROM roles WHERE role_name = ?)";

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

    @Override
    public void updateRole(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer userId = user.getId();
        String role = user.getRole().getRoleName();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ROLE);
            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, userId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement);
        }
    }

    @Override
    public List<Role> selectAvailableRoles(Role role) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Role> roles = new ArrayList<>();
        String roleName = role.getRoleName();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_AVAILABLE_ROLES);
            preparedStatement.setString(1, roleName);
            resultSet = preparedStatement.executeQuery();
            return RoleParser.listParser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
    }
}
