package com.lerkin.titllist.service.anime;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.anime.AnimeDao;
import com.lerkin.titllist.entity_db.Anime;

import java.util.List;

public class AnimeServiceImpl implements AnimeService{

    private final AnimeDao animeDao = DaoFactory.getAnimeDao();

    @Override
    public List<Anime> getAnimeByGenres(Integer idGenre) {
        return animeDao.selectAnimeByGenre(idGenre);
    }

    @Override
    public List<Anime> getAnimeByTypes(Integer idType) {
        return animeDao.selectAnimeByTypes(idType);
    }

    @Override
    public List<Anime> getAnimeByReleaseDate(Integer releaseDate) {
        return animeDao.selectAnimeByReleaseDate(releaseDate);
    }

    @Override
    public List<Anime> getAllAnime() {
        return animeDao.selectAllAnime();
    }

    @Override
    public void addNewAnime(Anime anime) {
        animeDao.insertNewAnime(anime);
    }
}