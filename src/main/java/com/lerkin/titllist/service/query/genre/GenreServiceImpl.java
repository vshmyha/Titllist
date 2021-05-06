package com.lerkin.titllist.service.query.genre;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.query.genre.GenreDao;
import com.lerkin.titllist.dao.query.type.TypeDao;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Genre;

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

    @Override
    public List<Anime> getByGenres(Integer id) {
        return genreDao.selectByGenre(id);
    }
}
