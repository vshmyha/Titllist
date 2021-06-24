package com.lerkin.titllist.dao;

import com.lerkin.titllist.dao.anime.AnimeDao;
import com.lerkin.titllist.dao.anime.AnimeDaoImpl;
import com.lerkin.titllist.dao.authority.AuthorityDao;
import com.lerkin.titllist.dao.authority.AuthorityDaoImpl;
import com.lerkin.titllist.dao.genre.GenreDao;
import com.lerkin.titllist.dao.genre.GenreDaoImpl;
import com.lerkin.titllist.dao.release_date.ReleaseDateDao;
import com.lerkin.titllist.dao.release_date.ReleaseDateDaoImpl;
import com.lerkin.titllist.dao.role.RoleDao;
import com.lerkin.titllist.dao.role.RoleDaoImpl;
import com.lerkin.titllist.dao.type.TypeDao;
import com.lerkin.titllist.dao.type.TypeDaoImpl;
import com.lerkin.titllist.dao.user.UserDao;
import com.lerkin.titllist.dao.user.UserDaoImpl;

public class DaoFactory {

    private static final UserDao USER_DAO = new UserDaoImpl();
    private static final TypeDao TYPE_DAO = new TypeDaoImpl();
    private static final GenreDao GENRE_DAO = new GenreDaoImpl();
    private static final ReleaseDateDao RELEASE_DATE_DAO = new ReleaseDateDaoImpl();
    private static final AnimeDao ANIME_DAO = new AnimeDaoImpl();
    private static final AuthorityDao AUTHORITY_DAO = new AuthorityDaoImpl();
    private static final RoleDao ROLE_DAO = new RoleDaoImpl();

    public static UserDao getUserDao() { return USER_DAO;}

    public static TypeDao getTypeDao() {return TYPE_DAO;}

    public static GenreDao getGenreDao() {return GENRE_DAO;}

    public static ReleaseDateDao getReleaseDateDao() {return RELEASE_DATE_DAO;}

    public static AnimeDao getAnimeDao() {return ANIME_DAO;}

    public static AuthorityDao getAuthorityDao() {
        return AUTHORITY_DAO;
    }

    public static RoleDao getRoleDao() { return ROLE_DAO; }
}
