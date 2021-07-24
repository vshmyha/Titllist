package com.lerkin.titllist.service.genre;

import com.lerkin.titllist.dao.entity.Genre;
import com.lerkin.titllist.dao.genre.GenreDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Override
    public List<Genre> getGenres() {
        return genreDao.selectGenres();
    }

    @Override
    public List<Genre> getGenresByAnimeId(Integer animeId) {
        return genreDao.selectGenresByAnimeId(animeId);
    }
}
