package com.lerkin.titllist.service.genre;

import com.lerkin.titllist.dao.entity.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getGenres();

    List<Genre> getGenresByAnimeId(Integer animeId);
}
