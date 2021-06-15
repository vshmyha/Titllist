package com.lerkin.titllist.dao.anime;

import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Genre;
import com.lerkin.titllist.entity_db.Type;
import com.lerkin.titllist.service.ServiceFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimeParser {
    public static List<Anime> parse(ResultSet resultSet) throws SQLException {
        List<Anime> animes = new ArrayList<>();
        while (resultSet.next()){
            String rusName = resultSet.getString("rus_name");
            String japName = resultSet.getString("jap_name");
            Integer episodesCount = resultSet.getInt("episods");
            Integer duration = resultSet.getInt("duration");
            Short releaseDate = resultSet.getShort("release_date");
            Integer idAnime = resultSet.getInt("id_anime");
            Type type = ServiceFactory.getTypeService().getTypeByAnimeId(idAnime);
            List<Genre> genres = ServiceFactory.getGenreService().getGenresByAnimeId(idAnime);
            Anime anime = new Anime(rusName, japName, type, episodesCount, duration, releaseDate, genres);
            anime.setId(idAnime);
            animes.add(anime);
        }
        return animes;
    }
}