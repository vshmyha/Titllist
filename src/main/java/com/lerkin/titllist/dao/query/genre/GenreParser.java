package com.lerkin.titllist.dao.query.genre;

import com.lerkin.titllist.entity_db.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class GenreParser {

    public static List<Genre> parse(ResultSet resultSet) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        while (resultSet.next()) {
            String genreName = resultSet.getString("genre");
            Integer id = resultSet.getInt("id_genre");
            Genre genre = new Genre(genreName);
            genre.setId(id);
            genres.add(genre);
        }
        return genres;
    }
}
