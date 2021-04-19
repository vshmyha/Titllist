package com.lerkin.titllist.dao;

import com.lerkin.titllist.dao.user.UserDao;
import com.lerkin.titllist.dao.user.UserDaoImpl;

public class DaoFactory {

    private static final UserDao USER_DAO = new UserDaoImpl();

    public static UserDao getUserDao() {
        return USER_DAO;
    }
}
