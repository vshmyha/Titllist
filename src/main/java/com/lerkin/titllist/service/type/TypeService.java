package com.lerkin.titllist.service.type;

import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Type;

import java.util.List;

public interface TypeService {

    List<Type> getTypes();

    Type getTypeByAnimeId(Integer id);

}
