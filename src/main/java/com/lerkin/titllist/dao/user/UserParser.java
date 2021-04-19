package com.lerkin.titllist.dao.user;

import com.lerkin.titllist.entity_db.User;

import java.sql.ResultSet;
import java.sql.SQLException;

class UserParser {

    public static User parse(ResultSet resultSet) throws SQLException {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        int id = resultSet.getInt("id");
        User user = new User(username, password, null);
        user.setId(id);
        return user;
    }
}
