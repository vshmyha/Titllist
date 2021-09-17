package com.lerkin.titllist.dao.role;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.dao.entity.Role;
import com.lerkin.titllist.dao.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository

public class RoleDaoImpl implements RoleDao {
    private static final String SELECT_ROLES_FOR_ADMIN = "SELECT role_name FROM roles WHERE role_name <> 'Admin' AND role_name <> 'Super Admin'";
    private static final String SELECT_ROLES_FOR_SUPER_ADMIN = "SELECT role_name FROM roles WHERE role_name <> 'Super Admin'";
    private static final String UPDATE_ROLE = "UPDATE users SET role_id = (SELECT id FROM roles WHERE name=?) WHERE id = ?";
    private static final String SELECT_AVAILABLE_ROLES = "SELECT name FROM roles JOIN available_roles ar ON roles.id = ar.available_role_id " +
            "WHERE ar.role_id = (SELECT id FROM roles WHERE name = ?)";

    @Override
    public List<Role> selectRolesForAdmin() {
        return loadRoles(SELECT_ROLES_FOR_ADMIN);
    }

    @Override
    public List<Role> selectRolesForSuperAdmin() {
        return loadRoles(SELECT_ROLES_FOR_SUPER_ADMIN);
    }

    private List<Role> loadRoles(String selectRolesForAdmin) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(selectRolesForAdmin);
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
