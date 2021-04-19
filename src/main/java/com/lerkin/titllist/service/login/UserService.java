package com.lerkin.titllist.service.login;

import com.lerkin.titllist.entity_db.User;

public interface UserService {

    User getUserByNameAndPass(User user);

    void registration(User user);
}
