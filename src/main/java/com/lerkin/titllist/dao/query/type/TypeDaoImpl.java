package com.lerkin.titllist.dao.query.type;

import com.lerkin.titllist.dao.config.ConnectionManager;
import com.lerkin.titllist.entity_db.Anime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDaoImpl implements TypeDao {

    private static final String SELECT_ALL_TYPE = "SELECT type_name FROM type";
    private static final String SELECT_ANIME_OVA = "SELECT a.id_anime, a.rus_name, a.jap_name, t.type_name, a.episods, a.duration, a.release_dateFROM anime_base AS a INNER JOIN (SELECT type_name FROM type WHERE id_type = 3) AS t WHERE type_id = 3";
    private static final String SELECT_ANIME_ONA = "";
    private static final String SELECT_ANIME_FILM = "";
    private static final String SELECT_ANIME_TV_SERIAL = "";
    private static final String SELECT_ANIME_SPESHL = "";


    @Override
    public List<String> selectTypes() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> types = new ArrayList<>();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_TYPE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String type = resultSet.getString(1);
                types.add(type);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
        return types;
    }

    @Override
    public List<Anime> selectAnimeOVA() {
        return null;
    }

    @Override
    public List<Anime> selectAnimeONA() {
        return null;
    }

    @Override
    public List<Anime> selectAnimeFilm() {
        return null;
    }

    @Override
    public List<Anime> selectAnimeTVSerial() {
        return null;
    }

    @Override
    public List<Anime> selectAnimeSpeshl() {
        return null;
    }


}
