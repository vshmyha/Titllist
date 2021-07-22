package com.lerkin.titllist.service.genre;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.genre.GenreDao;
import com.lerkin.titllist.dao.entity.Genre;

import java.util.List;

public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao = DaoFactory.getGenreDao();

    @Override
    public List<Genre> getGenres() {
        return genreDao.selectGenres();
    }

    @Override
    public List<Genre> getGenresByAnimeId(Integer animeId) {
        return genreDao.selectGenresByAnimeId(animeId);
    }
}
