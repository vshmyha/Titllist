package com.lerkin.titllist.dao.anime;

import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Status;
import com.lerkin.titllist.entity_db.User;

import java.util.List;

public interface AnimeDao {

    List<Anime> selectAnimeByGenre(Integer idGenre);

    List<Anime> selectAnimeByTypes(Integer idType);

    List<Anime> selectAnimeByReleaseDate(Integer releaseDate);

    List<Anime> selectAllAnime();

    void insertNewAnime(Anime anime);

    Anime selectAnimeById(Integer animeId);

    void insertAnimeToUserTitllist(User user, Integer animeId, Status status);
}
