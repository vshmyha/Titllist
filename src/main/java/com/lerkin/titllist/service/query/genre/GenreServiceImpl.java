package com.lerkin.titllist.service.query.genre;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.query.genre.GenreDao;
import com.lerkin.titllist.dao.query.type.TypeDao;

import java.util.List;

public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao = DaoFactory.getGenreDao();

    @Override
    public List<String> getGenres() {
        return genreDao.selectGenres();
    }
}
