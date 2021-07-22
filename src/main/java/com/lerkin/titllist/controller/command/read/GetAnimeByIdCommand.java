package com.lerkin.titllist.controller.command.read;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.dao.entity.Anime;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.dao.entity.UserAnime;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.anime.AnimeService;
import com.lerkin.titllist.service.user_anime.UserAnimeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetAnimeByIdCommand implements Command {
    private final AnimeService animeService = ServiceFactory.getAnimeService();
    private final UserAnimeService userAnimeService = ServiceFactory.getUserAnimeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        Integer animeId = Integer.valueOf(req.getParameter("id"));
        UserAnime userAnime = userAnimeService.getUserAnime(animeId, userId);
        if (userAnime == null) {
            Anime anime = animeService.getAnimeById(animeId);
            userAnime = new UserAnime(anime, null, userId);
        }
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, userAnime);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
