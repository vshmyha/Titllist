package com.lerkin.titllist.dao.type;

import com.lerkin.titllist.entity_db.Type;

import java.util.List;

public interface TypeDao {

    List<Type> selectTypes();

    Type selectTypeByAnimeId(Integer id);

}
