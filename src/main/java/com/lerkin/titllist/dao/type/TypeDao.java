package com.lerkin.titllist.dao.type;

import com.lerkin.titllist.dao.entity.Type;

import java.util.List;

public interface TypeDao {

    List<Type> selectTypes();

    Type selectTypeByAnimeId(Integer id);

}
