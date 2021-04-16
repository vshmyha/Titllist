package com.lerkin.titllist.dao;

import com.lerkin.titllist.dao.login.LoginDao;
import com.lerkin.titllist.dao.login.LoginDaoImpl;

public class DaoFactory {

    private static final LoginDao LOGIN_DAO = new LoginDaoImpl();

    public static LoginDao getLoginDao() {
        return LOGIN_DAO;
    }
}
