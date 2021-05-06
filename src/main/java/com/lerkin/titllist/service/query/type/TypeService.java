package com.lerkin.titllist.service.query.type;

import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Type;

import java.util.List;

public interface TypeService {

    List<Type> getTypes();

    List<Anime> getByTypes(Integer id);

    Type getTypeByAnimeId(Integer id);

}
