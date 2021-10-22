package com.lerkin.titllist.dao.user;

import com.lerkin.titllist.dto.Role;
import com.lerkin.titllist.dao.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class UserParser {

	public static User parse(ResultSet resultSet) throws SQLException {

		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		int id = resultSet.getInt("id");
		User user = new User(username, password, null);
		user.setId(id);
		return user;
	}

	public static List<User> listParse(ResultSet resultSet) throws SQLException {

		List<User> users = new ArrayList<>();
		while (resultSet.next()) {
			User user = new User();
			String username = resultSet.getString("username");
			int id = resultSet.getInt("id");
			String userRole = resultSet.getString("role_name");
			user.setUserName(username);
			user.setId(id);
			Role role = Role.byText(userRole);
			user.setRole(role);
			users.add(user);
		}
		return users;
	}
}
