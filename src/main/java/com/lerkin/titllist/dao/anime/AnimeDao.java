package com.lerkin.titllist.dao.anime;

import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dto.Status;
import com.lerkin.titllist.dao.entity.User;

import java.util.List;

public interface AnimeDao {

	List<Anime> selectAnimeByGenre(Integer idGenre);

	List<Anime> selectAnimeByTypes(Integer idType);

	List<Anime> selectAnimeByReleaseDate(Integer releaseDate);

	List<Anime> selectAllAnime();

	void insertNewAnime(Anime anime);

	Anime selectAnimeById(Integer animeId);

	void insertAnimeToUserTitllist(User user, Integer animeId, Status status);

	List<Anime> selectAnimeFromUserTitllist(User user);

	List<Anime> selectAnimeFromUserTitllistByStatus(User user, Status status);

	List<Anime> selectAnimesByName(String animeName);
}
