package com.lerkin.titllist.service.user;

import com.lerkin.titllist.dao.entity.User;

import java.util.List;

public interface UserService {

    User getUserByNameAndPass(User user);

    void registration(User user);

    void changePassword(User user);

    void checkCurrentPassword(User user);

    List<User> getUsersAndRoles();
}
