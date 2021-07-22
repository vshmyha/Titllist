package com.lerkin.titllist.service.user_anime;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.genre.GenreDao;
import com.lerkin.titllist.dao.user_anime.UserAnimeDao;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Genre;
import com.lerkin.titllist.entity_db.Status;
import com.lerkin.titllist.entity_db.UserAnime;
import com.lerkin.titllist.exception.UserFriendlyException;

import java.util.List;

public class UserAnimeServiceImpl implements UserAnimeService {
    private final UserAnimeDao userAnimeDao = DaoFactory.getUserAnimeDao();
    private final GenreDao genreDao = DaoFactory.getGenreDao();

    @Override
    public UserAnime getUserAnime(Integer animeId, Integer userId) {
        UserAnime userAnime = userAnimeDao.selectUserAnime(animeId, userId);
        List<Genre> genres = genreDao.selectGenresByAnimeId(animeId);
        Anime anime = userAnime.getAnime();
        anime.setGenres(genres);
        return userAnime;
    }

    @Override
    public void checkCurrentAnimeStatus(Integer animeId, Integer userId, Status status) {
        Status currentStatus = userAnimeDao.selectCurrentAnimeStatus(animeId, userId);
        if(currentStatus.equals(status)){
            String statusName = currentStatus.getStatusName();
            throw new UserFriendlyException("This title has already been added to the Titllist '" + statusName + "'");
        }
    }

    @Override
    public void changeAnimeStatusInTitllist(Integer animeId, Integer userId, Status status) {
        userAnimeDao.updateCurrentStatus(animeId, userId, status);
    }
}
