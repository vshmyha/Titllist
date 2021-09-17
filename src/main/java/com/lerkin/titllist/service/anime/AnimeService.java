package com.lerkin.titllist.service.anime;

import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.dao.entity_db.AnimeEntity;

import java.util.List;

public interface AnimeService {

    List<AnimeEntity> getAnimeByGenres(Integer idGenre);

    List<Anime> getAnimeByTypes(Integer idType);

    List<Anime> getAnimeByReleaseDate(Integer releaseDate);

    List<AnimeEntity> getAllAnime();

    void addNewAnime(Anime anime);

    AnimeEntity getAnimeById(Integer animeId);

    void addAnimeToUserTitllist(User user, Integer animeId, Status status);

    List<Anime> getAnimeFromUserTitllist(User user);

    List<Anime> getAnimeFromUserTitllistByStatus(User user, Status status);

    List<Anime> searchAnimesByName(String animeName);

}
