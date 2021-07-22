package com.lerkin.titllist.service.user_anime;

import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.entity.UserAnime;

public interface UserAnimeService {

    UserAnime getUserAnime(Integer animeId, Integer userId);

    void checkCurrentAnimeStatus(Integer animeId, Integer userId, Status status);

    void changeAnimeStatusInTitllist(Integer animeId, Integer userId, Status status);
}
