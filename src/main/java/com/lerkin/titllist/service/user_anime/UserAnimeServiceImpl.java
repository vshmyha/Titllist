package com.lerkin.titllist.service.user_anime;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.user_anime.UserAnimeDao;
import com.lerkin.titllist.entity_db.Status;
import com.lerkin.titllist.entity_db.UserAnime;
import com.lerkin.titllist.exception.UserFriendlyException;

public class UserAnimeServiceImpl implements UserAnimeService {
    private final UserAnimeDao userAnimeDao = DaoFactory.getUserAnimeDao();

    @Override
    public UserAnime getUserAnime(Integer animeId, Integer userId) {
        return userAnimeDao.selectUserAnime(animeId, userId);
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
