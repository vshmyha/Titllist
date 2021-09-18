package com.lerkin.titllist.service.type;

import com.lerkin.titllist.dao.entity_db.TypeEntity;

import java.util.List;

public interface TypeService {

	List<TypeEntity> getTypes();

	TypeEntity getTypeByAnimeId(Integer id);

}
