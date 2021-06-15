package com.lerkin.titllist.dao.anime;

import com.lerkin.titllist.entity_db.Anime;

import java.util.List;

public interface AnimeDao {

    List<Anime> selectAnimeByGenre(Integer idGenre);

    List<Anime> selectAnimeByTypes(Integer idType);

    List<Anime> selectAnimeByReleaseDate(Integer releaseDate);

    List<Anime> selectAllAnime();

    void insertNewAnime(Anime anime);
}
