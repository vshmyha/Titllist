package com.lerkin.titllist.dao.genre;

import com.lerkin.titllist.dao.entity.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> selectGenres();

    List<Genre> selectGenresByAnimeId(Integer animeId);
}
