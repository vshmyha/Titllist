package com.lerkin.titllist.service.anime;

import com.lerkin.titllist.dao.anime.AnimeDao;
import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.dao.entity_db.AnimeEntity;
import com.lerkin.titllist.dao.genre.GenreDao;
import com.lerkin.titllist.repository.AnimeRepository;
import com.lerkin.titllist.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AnimeServiceImpl implements AnimeService {

	private final AnimeRepository animeRepository;
	private final AnimeDao animeDao;

	@Override
	public List<AnimeEntity> getAnimeByGenres(Integer idGenre) {

		return animeRepository.findByGenreId(idGenre);
	}

	@Override
	public List<Anime> getAnimeByTypes(Integer idType) {

		return animeDao.selectAnimeByTypes(idType);
	}

	@Override
	public List<Anime> getAnimeByReleaseDate(Integer releaseDate) {

		return animeDao.selectAnimeByReleaseDate(releaseDate);
	}

	@Override
	public List<AnimeEntity> getAllAnime() {

		return animeRepository.findAll();
	}

	@Override
	public void addNewAnime(Anime anime) {

		animeDao.insertNewAnime(anime);
	}

	@Override
	public AnimeEntity getAnimeById(Integer animeId) {

		AnimeEntity anime = animeRepository.findAnimeEntityById(animeId);
		return anime;
	}

	@Override
	public void addAnimeToUserTitllist(User user, Integer animeId, Status status) {

		animeDao.insertAnimeToUserTitllist(user, animeId, status);
	}

	@Override
	public List<Anime> getAnimeFromUserTitllist(User user) {

		return animeDao.selectAnimeFromUserTitllist(user);
	}

	@Override
	public List<Anime> getAnimeFromUserTitllistByStatus(User user, Status status) {

		return animeDao.selectAnimeFromUserTitllistByStatus(user, status);
	}

	@Override
	public List<Anime> searchAnimesByName(String animeName) {

		return animeDao.selectAnimesByName(animeName);
	}
}
