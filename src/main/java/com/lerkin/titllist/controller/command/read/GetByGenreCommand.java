package com.lerkin.titllist.controller.command.read;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.anime.AnimeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetByGenreCommand implements Command {
    private final AnimeService animeService = ServiceFactory.getAnimeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer idGenre = Integer.parseInt(req.getParameter("id"));
        List<Anime> animeByGenres = animeService.getAnimeByGenres(idGenre);
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, animeByGenres);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
