package com.lerkin.titllist.dao.user_anime;

import com.lerkin.titllist.entity_db.Status;
import com.lerkin.titllist.entity_db.UserAnime;

public interface UserAnimeDao {

    UserAnime selectUserAnime(Integer animeId, Integer userId);

    Status selectCurrentAnimeStatus(Integer animeId, Integer userId);

    void updateCurrentStatus(Integer animeId, Integer userId, Status status);
}
