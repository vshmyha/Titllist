package com.lerkin.titllist.service.user_anime;

import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity.Genre;
import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.entity.UserAnime;
import com.lerkin.titllist.dao.genre.GenreDao;
import com.lerkin.titllist.dao.user_anime.UserAnimeDao;
import com.lerkin.titllist.exception.UserFriendlyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor

public class UserAnimeServiceImpl implements UserAnimeService {

	private final UserAnimeDao userAnimeDao;
	private final GenreDao genreDao;

	@Override
	public UserAnime getUserAnime(Integer animeId, Integer userId) {

		UserAnime userAnime = userAnimeDao.selectUserAnime(animeId, userId);
		if (Objects.nonNull(userAnime)) {
			List<Genre> genres = genreDao.selectGenresByAnimeId(animeId);
			Anime anime = userAnime.getAnime();
			anime.setGenres(genres);
		}
		return userAnime;
	}

	@Override
	public void checkCurrentAnimeStatus(Integer animeId, Integer userId, Status status) {

		Status currentStatus = userAnimeDao.selectCurrentAnimeStatus(animeId, userId);
		if (currentStatus.equals(status)) {
			String statusName = currentStatus.getStatusName();
			throw new UserFriendlyException("This title has already been added to the Titllist '" + statusName + "'");
		}
	}

	@Override
	public void changeAnimeStatusInTitllist(Integer animeId, Integer userId, Status status) {

		userAnimeDao.updateCurrentStatus(animeId, userId, status);
	}
}
