package com.lerkin.titllist.dao.genre;

import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> selectGenres();

    List<Genre> selectGenresByAnimeId(Integer animeId);
}
