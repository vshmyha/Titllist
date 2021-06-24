package com.lerkin.titllist.service;

import com.lerkin.titllist.service.anime.AnimeService;
import com.lerkin.titllist.service.anime.AnimeServiceImpl;
import com.lerkin.titllist.service.authority.AuthorityService;
import com.lerkin.titllist.service.authority.AuthorityServiceImpl;
import com.lerkin.titllist.service.role.RoleService;
import com.lerkin.titllist.service.role.RoleServiceImpl;
import com.lerkin.titllist.service.user.UserService;
import com.lerkin.titllist.service.user.UserServiceImpl;
import com.lerkin.titllist.service.genre.GenreService;
import com.lerkin.titllist.service.genre.GenreServiceImpl;
import com.lerkin.titllist.service.release_date.ReleaseDateService;
import com.lerkin.titllist.service.release_date.ReleaseDateServiceImp;
import com.lerkin.titllist.service.type.TypeService;
import com.lerkin.titllist.service.type.TypeServiceImpl;

public class ServiceFactory {

    private static final UserService USER_SERVICE = new UserServiceImpl();
    private static final TypeService TYPE_SERVICE = new TypeServiceImpl();
    private static final GenreService GENRE_SERVICE = new GenreServiceImpl();
    private static final ReleaseDateService RELEASE_DATE_SERVICE = new ReleaseDateServiceImp();
    private static final AnimeService ANIME_SERVICE = new AnimeServiceImpl();
    private static final AuthorityService AUTHORITY_SERVICE = new AuthorityServiceImpl();
    private static final RoleService ROLE_SERVICE = new RoleServiceImpl();

    public static UserService getUserService() {
        return USER_SERVICE;
    }

    public static TypeService getTypeService() {
        return TYPE_SERVICE;
    }

    public static GenreService getGenreService() {
        return GENRE_SERVICE;
    }

    public static ReleaseDateService getReleaseDateService() {
        return RELEASE_DATE_SERVICE;
    }

    public static AnimeService getAnimeService() {
        return ANIME_SERVICE;
    }

    public static AuthorityService getAuthorityService() {
        return AUTHORITY_SERVICE;
    }

    public static RoleService getRoleService() {
        return ROLE_SERVICE;
    }
}
