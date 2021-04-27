package com.lerkin.titllist.dao.user;

import com.lerkin.titllist.entity_db.User;

public interface UserDao {

    User selectUser(String username, String password);

    boolean isUserExist(String username);

    void addUser(User user);
}
