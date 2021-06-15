package com.lerkin.titllist.service.anime;

import com.lerkin.titllist.entity_db.Anime;

import java.util.List;

public interface AnimeService {

    List<Anime> getAnimeByGenres(Integer idGenre);

    List<Anime> getAnimeByTypes(Integer idType);

    List<Anime> getAnimeByReleaseDate(Integer releaseDate);

    List<Anime> getAllAnime();

    void addNewAnime(Anime anime);
}
