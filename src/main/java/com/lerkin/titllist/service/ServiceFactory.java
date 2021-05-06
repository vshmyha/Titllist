package com.lerkin.titllist.service;

import com.lerkin.titllist.service.login.UserService;
import com.lerkin.titllist.service.login.UserServiceImpl;
import com.lerkin.titllist.service.query.genre.GenreService;
import com.lerkin.titllist.service.query.genre.GenreServiceImpl;
import com.lerkin.titllist.service.query.release_date.ReleaseDateService;
import com.lerkin.titllist.service.query.release_date.ReleaseDateServiceImp;
import com.lerkin.titllist.service.query.type.TypeService;
import com.lerkin.titllist.service.query.type.TypeServiceImpl;

public class ServiceFactory {

    private static final UserService LOGIN_SERVICE = new UserServiceImpl();
    private static final TypeService TYPE_SERVICE = new TypeServiceImpl();
    private static final GenreService GENRE_SERVICE = new GenreServiceImpl();
    private static final ReleaseDateService RELEASE_DATE_SERVICE = new ReleaseDateServiceImp();

    public static UserService getLoginService() {
        return LOGIN_SERVICE;
    }

    public static TypeService getTypeService() {
        return TYPE_SERVICE;
    }

    public static GenreService getGenreService() {
        return GENRE_SERVICE;
    }

    public static ReleaseDateService getReleaseDateService(){ return RELEASE_DATE_SERVICE;}

}
