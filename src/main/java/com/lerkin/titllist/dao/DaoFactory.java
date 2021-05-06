package com.lerkin.titllist.dao;

import com.lerkin.titllist.dao.query.genre.GenreDao;
import com.lerkin.titllist.dao.query.genre.GenreDaoImpl;
import com.lerkin.titllist.dao.query.release_date.ReleaseDateDao;
import com.lerkin.titllist.dao.query.release_date.ReleaseDateDaoImpl;
import com.lerkin.titllist.dao.query.type.TypeDao;
import com.lerkin.titllist.dao.query.type.TypeDaoImpl;
import com.lerkin.titllist.dao.user.UserDao;
import com.lerkin.titllist.dao.user.UserDaoImpl;

public class DaoFactory {

    private static final UserDao USER_DAO = new UserDaoImpl();
    private static final TypeDao TYPE_DAO = new TypeDaoImpl();
    private static final GenreDao GENRE_DAO = new GenreDaoImpl();
    private static final ReleaseDateDao RELEASE_DATE_DAO = new ReleaseDateDaoImpl();

    public static UserDao getUserDao() { return USER_DAO;}

    public static TypeDao getTypeDao() {return TYPE_DAO;}

    public static GenreDao getGenreDao() {return GENRE_DAO;}

    public static ReleaseDateDao getReleaseDateDao() {return RELEASE_DATE_DAO;}
}
