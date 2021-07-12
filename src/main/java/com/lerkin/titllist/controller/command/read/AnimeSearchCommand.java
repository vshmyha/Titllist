package com.lerkin.titllist.controller.command.read;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.anime.AnimeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AnimeSearchCommand implements Command {

    private final AnimeService animeService = ServiceFactory.getAnimeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String animeName = req.getParameter("animeName");
        List<Anime> animes = animeService.searchAnimesByName(animeName);
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, animes);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
