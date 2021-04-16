package com.lerkin.titllist.service;

import com.lerkin.titllist.service.login.LoginService;
import com.lerkin.titllist.service.login.LoginServiceImpl;

public class ServiceFactory {

    private static final LoginService LOGIN_SERVICE = new LoginServiceImpl();

    public static LoginService getLoginService() {
        return LOGIN_SERVICE;
    }
}
