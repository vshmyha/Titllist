package com.lerkin.titllist.service.query.type;

import com.lerkin.titllist.entity_db.Anime;

import java.util.List;

public interface TypeService {

    List<String> getTypes();

    List<Anime> getAnimeOVA();

    List<Anime> getAnimeONA();

    List<Anime> getAnimeFilm();

    List<Anime> getAnimeTVSerial();

    List<Anime> getAnimeSpeshl();
}
