package com.lerkin.titllist.dao.query.type;

import com.lerkin.titllist.entity_db.Anime;

import java.util.List;

public interface TypeDao {

    List<String> selectTypes();

    List<Anime> selectAnimeOVA();

    List<Anime> selectAnimeONA();

    List<Anime> selectAnimeFilm();

    List<Anime> selectAnimeTVSerial();

    List<Anime> selectAnimeSpeshl();
}
