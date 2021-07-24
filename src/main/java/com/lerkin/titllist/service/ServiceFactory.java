package com.lerkin.titllist.service;

import com.lerkin.titllist.service.anime.AnimeService;
import com.lerkin.titllist.service.anime.AnimeServiceImpl;
import com.lerkin.titllist.service.authority.AuthorityService;
import com.lerkin.titllist.service.authority.AuthorityServiceImpl;
import com.lerkin.titllist.service.genre.GenreService;
import com.lerkin.titllist.service.genre.GenreServiceImpl;
import com.lerkin.titllist.service.release_date.ReleaseDateService;
import com.lerkin.titllist.service.release_date.ReleaseDateServiceImp;
import com.lerkin.titllist.service.role.RoleService;
import com.lerkin.titllist.service.role.RoleServiceImpl;
import com.lerkin.titllist.service.status.StatusService;
import com.lerkin.titllist.service.status.StatusServiceImpl;
import com.lerkin.titllist.service.type.TypeService;
import com.lerkin.titllist.service.type.TypeServiceImpl;
import com.lerkin.titllist.service.user.UserService;
import com.lerkin.titllist.service.user.UserServiceImpl;
import com.lerkin.titllist.service.user_anime.UserAnimeService;
import com.lerkin.titllist.service.user_anime.UserAnimeServiceImpl;

public class ServiceFactory {

    private static final UserService USER_SERVICE = null;
    private static final TypeService TYPE_SERVICE = null;
    private static final GenreService GENRE_SERVICE = null;
    private static final ReleaseDateService RELEASE_DATE_SERVICE = null;
    private static final AnimeService ANIME_SERVICE = null;
    private static final AuthorityService AUTHORITY_SERVICE = null;
    private static final RoleService ROLE_SERVICE = null;
    private static final StatusService STATUS_SERVICE = null;
    private static final UserAnimeService USER_ANIME_SERVICE = null;

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

    public static StatusService getStatusService() {
        return STATUS_SERVICE;
    }

    public static UserAnimeService getUserAnimeService() {
        return USER_ANIME_SERVICE;
    }
}
