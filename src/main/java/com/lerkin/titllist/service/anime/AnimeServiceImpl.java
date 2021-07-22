package com.lerkin.titllist.service.anime;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.anime.AnimeDao;
import com.lerkin.titllist.dao.genre.GenreDao;
import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity.Genre;
import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.entity.User;

import java.util.List;

public class AnimeServiceImpl implements AnimeService {

    private final AnimeDao animeDao = DaoFactory.getAnimeDao();
    private final GenreDao genreDao = DaoFactory.getGenreDao();

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

    @Override
    public Anime getAnimeById(Integer animeId) {
        Anime anime = animeDao.selectAnimeById(animeId);
        List<Genre> genres = genreDao.selectGenresByAnimeId(animeId);
        anime.setGenres(genres);
        return anime;
    }

    @Override
    public void addAnimeToUserTitllist(User user, Integer animeId, Status status) {
        animeDao.insertAnimeToUserTitllist(user, animeId, status);
    }

    @Override
    public List<Anime> getAnimeFromUserTitllist(User user) {
        return animeDao.selectAnimeFromUserTitllist(user);
    }

    @Override
    public List<Anime> getAnimeFromUserTitllistByStatus(User user, Status status) {
        return animeDao.selectAnimeFromUserTitllistByStatus(user, status);
    }

    @Override
    public List<Anime> searchAnimesByName(String animeName) {
        return animeDao.selectAnimesByName(animeName);
    }
}
