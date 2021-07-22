package com.lerkin.titllist.service.type;

import com.lerkin.titllist.dao.entity.Type;

import java.util.List;

public interface TypeService {

    List<Type> getTypes();

    Type getTypeByAnimeId(Integer id);

}
