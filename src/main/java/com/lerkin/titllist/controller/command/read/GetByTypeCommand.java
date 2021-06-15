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

public class GetByTypeCommand implements Command {
    private final AnimeService animeService = ServiceFactory.getAnimeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer idType = Integer.parseInt(req.getParameter("id"));
        List<Anime> animeByTypes = animeService.getAnimeByTypes(idType);
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, animeByTypes);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
