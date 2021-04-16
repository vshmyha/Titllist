package com.lerkin.titllist.dao.login;

import com.lerkin.titllist.entity_db.User;

public interface LoginDao {

    void login(User user);

    void registration(User user);
}
