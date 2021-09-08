package com.lerkin.titllist.dao.anime;

import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimeParser {

    public static List<Anime> listParse(ResultSet resultSet) throws SQLException {
        List<Anime> animes = new ArrayList<>();
        while (resultSet.next()) {
            String rusName = resultSet.getString("rus_name");
            String japName = resultSet.getString("jap_name");
            Integer idAnime = resultSet.getInt("id");
            Anime anime = new Anime(rusName, japName);
            anime.setId(idAnime);
            animes.add(anime);
        }
        return animes;
    }

    public static Anime parse(ResultSet resultSet, boolean cursorMoved) throws SQLException {

        Anime anime = null;
        if (cursorMoved || resultSet.next()) {
            String rusName = resultSet.getString("rus_name");
            String japName = resultSet.getString("jap_name");
            Integer episodesCount = resultSet.getInt("episodes");
            Integer duration = resultSet.getInt("duration");
            Short releaseDate = resultSet.getShort("release_date");
            Integer idAnime = resultSet.getInt("id");
            String typeName = resultSet.getString("name");
            Type type = new Type();
            type.setTypeName(typeName);
            anime = new Anime(rusName, japName, type, episodesCount, duration, releaseDate);
            anime.setId(idAnime);
        }
        return anime;
    }

    public static Anime parse(ResultSet resultSet) throws SQLException {

        return parse(resultSet, false);
    }

}
