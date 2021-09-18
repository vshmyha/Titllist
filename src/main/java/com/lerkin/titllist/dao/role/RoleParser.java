package com.lerkin.titllist.dao.role;

import com.lerkin.titllist.dao.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleParser {

	public static Role parse(ResultSet resultSet) throws SQLException {

		String roleName = resultSet.getString("name");
		Role role = Role.byText(roleName);
		return role;
	}

	public static List<Role> listParser(ResultSet resultSet) throws SQLException {

		List<Role> roles = new ArrayList<>();
		while (resultSet.next()) {
			String roleName = resultSet.getString("name");
			Role role = Role.byText(roleName);
			roles.add(role);
		}
		return roles;
	}
}
