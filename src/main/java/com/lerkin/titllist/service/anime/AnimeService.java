package com.lerkin.titllist.service.anime;

import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.entity.User;

import java.util.List;

public interface AnimeService {

    List<Anime> getAnimeByGenres(Integer idGenre);

    List<Anime> getAnimeByTypes(Integer idType);

    List<Anime> getAnimeByReleaseDate(Integer releaseDate);

    List<Anime> getAllAnime();

    void addNewAnime(Anime anime);

    Anime getAnimeById(Integer animeId);

    void addAnimeToUserTitllist(User user, Integer animeId, Status status);

    List<Anime> getAnimeFromUserTitllist(User user);

    List<Anime> getAnimeFromUserTitllistByStatus(User user, Status status);

    List<Anime> searchAnimesByName(String animeName);

}
