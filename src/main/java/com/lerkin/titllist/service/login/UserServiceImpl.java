package com.lerkin.titllist.service.login;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.user.UserDao;
import com.lerkin.titllist.entity_db.User;

import java.util.Base64;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = DaoFactory.getUserDao();

    @Override
    public User getUserByNameAndPass(User user) {
        String username = user.getUserName();
        String password = user.getPassword();
        byte[] encode = Base64.getEncoder().encode(password.getBytes());
        String encodedPassword = new String(encode);
        User resultUser = userDao.selectUser(username, encodedPassword);
        return resultUser;
    }

    @Override
    public void registration(User user) {

    }
}
