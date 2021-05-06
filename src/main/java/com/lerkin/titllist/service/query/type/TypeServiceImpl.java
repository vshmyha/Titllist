package com.lerkin.titllist.service.query.type;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.query.type.TypeDao;
import com.lerkin.titllist.dao.user.UserDao;
import com.lerkin.titllist.entity_db.Anime;

import java.util.List;

public class TypeServiceImpl implements TypeService {

    private final TypeDao typeDao = DaoFactory.getTypeDao();

    @Override
    public List<String> getTypes() {
        return typeDao.selectTypes();
    }

    @Override
    public List<Anime> getAnimeOVA() {
        return typeDao.selectAnimeOVA();
    }

    @Override
    public List<Anime> getAnimeONA() {
        return typeDao.selectAnimeONA();
    }

    @Override
    public List<Anime> getAnimeFilm() {
        return typeDao.selectAnimeFilm();
    }

    @Override
    public List<Anime> getAnimeTVSerial() {
        return typeDao.selectAnimeTVSerial();
    }

    @Override
    public List<Anime> getAnimeSpeshl() {
        return typeDao.selectAnimeSpeshl();
    }


}

