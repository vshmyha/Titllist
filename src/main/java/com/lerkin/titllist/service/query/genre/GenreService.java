package com.lerkin.titllist.service.query.genre;

import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getGenres();

    List<Genre> getGenresByAnimeId(Integer animeId);

    List<Anime> getByGenres(Integer id);
}
