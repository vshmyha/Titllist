package com.lerkin.titllist.service.genre;

import com.lerkin.titllist.entity_db.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getGenres();

    List<Genre> getGenresByAnimeId(Integer animeId);
}
