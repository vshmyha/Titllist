package com.lerkin.titllist.dao.query.type;

import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Type;

import java.util.List;

public interface TypeDao {

    List<Type> selectTypes();

    List<Anime> selectByTypes(Integer id);

    Type selectTypeByAnimeId(Integer id);

}
