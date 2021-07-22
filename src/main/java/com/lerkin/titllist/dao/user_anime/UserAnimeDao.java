package com.lerkin.titllist.dao.user_anime;

import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.entity.UserAnime;

public interface UserAnimeDao {

    UserAnime selectUserAnime(Integer animeId, Integer userId);

    Status selectCurrentAnimeStatus(Integer animeId, Integer userId);

    void updateCurrentStatus(Integer animeId, Integer userId, Status status);
}
