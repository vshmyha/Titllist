package com.lerkin.titllist.service.release_date;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.release_date.ReleaseDateDao;

import java.util.List;

public class ReleaseDateServiceImp implements ReleaseDateService {
    private final ReleaseDateDao releaseDateDao = DaoFactory.getReleaseDateDao();

    @Override
    public List<Short> getReleaseDate() {
        return releaseDateDao.selectReleaseDates();
    }
}
