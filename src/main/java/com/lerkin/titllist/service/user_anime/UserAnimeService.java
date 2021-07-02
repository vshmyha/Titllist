package com.lerkin.titllist.service.user_anime;

import com.lerkin.titllist.entity_db.Status;
import com.lerkin.titllist.entity_db.UserAnime;

public interface UserAnimeService {

    UserAnime getUserAnime(Integer animeId, Integer userId);

    void checkCurrentAnimeStatus(Integer animeId, Integer userId, Status status);

    void changeAnimeStatusInTitllist(Integer animeId, Integer userId, Status status);
}
