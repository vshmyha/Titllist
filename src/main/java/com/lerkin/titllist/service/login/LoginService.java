package com.lerkin.titllist.service.login;

import com.lerkin.titllist.entity_db.User;

public interface LoginService {

    void login(User user);

    void registration(User user);
}
