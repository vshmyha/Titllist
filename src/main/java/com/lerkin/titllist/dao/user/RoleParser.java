package com.lerkin.titllist.dao.user;

import com.lerkin.titllist.entity_db.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleParser {

    public static Role parse(ResultSet resultSet) throws SQLException {
        String roleName = resultSet.getString("role_name");
        Role role = Role.byText(roleName);
        return role;
    }
}
