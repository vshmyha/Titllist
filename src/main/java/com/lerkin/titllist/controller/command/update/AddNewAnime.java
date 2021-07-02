package com.lerkin.titllist.controller.command.update;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Genre;
import com.lerkin.titllist.entity_db.Type;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.anime.AnimeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddNewAnime implements Command {
    private final AnimeService animeService = ServiceFactory.getAnimeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String[] genresId = req.getParameterValues("genre");
        String rusName = req.getParameter("rusName");
        String japNAme = req.getParameter("japName");
        Integer typeId = Integer.valueOf(req.getParameter("type"));
        Integer duration = Integer.valueOf(req.getParameter("duration"));
        Integer episodes = Integer.valueOf(req.getParameter("episodes"));
        Short releaseDate = Short.valueOf(req.getParameter("releaseDate"));
        Type type = new Type();
        type.setId(typeId);
        List<Genre> genres = new ArrayList<>();
        for (String s : genresId) {
            Genre genre = new Genre();
            Integer id = Integer.valueOf(s);
            genre.setId(id);
            genres.add(genre);
        }
        Anime anime = new Anime(rusName, japNAme, type, episodes, duration, releaseDate, genres);
        animeService.addNewAnime(anime);
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
