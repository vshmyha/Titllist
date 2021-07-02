package com.lerkin.titllist.dao.user_anime;

import com.lerkin.titllist.dao.anime.AnimeParser;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Status;
import com.lerkin.titllist.entity_db.UserAnime;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAnimeParser {

    public static UserAnime parser(ResultSet resultSet) throws SQLException {

        if (resultSet.next()) {
            Anime anime = AnimeParser.parse(resultSet, true);
            Integer userId = resultSet.getInt("id_user");
            String animeStatus = resultSet.getString("status");
            Status status = Status.byText(animeStatus);
            UserAnime userAnime = new UserAnime(anime, status, userId);
            return userAnime;
        }
        return null;
    }
}
