package com.lerkin.titllist.service;

import com.lerkin.titllist.service.login.UserService;
import com.lerkin.titllist.service.login.UserServiceImpl;

public class ServiceFactory {

    private static final UserService LOGIN_SERVICE = new UserServiceImpl();

    public static UserService getLoginService() {
        return LOGIN_SERVICE;
    }
}
