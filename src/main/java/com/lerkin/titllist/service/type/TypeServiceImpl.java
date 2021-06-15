package com.lerkin.titllist.service.type;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.type.TypeDao;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Type;

import java.util.List;

public class TypeServiceImpl implements TypeService {

    private final TypeDao typeDao = DaoFactory.getTypeDao();

    @Override
    public List<Type> getTypes() {
        return typeDao.selectTypes();
    }

    @Override
    public Type getTypeByAnimeId(Integer id) {
        return typeDao.selectTypeByAnimeId(id);
    }

}

