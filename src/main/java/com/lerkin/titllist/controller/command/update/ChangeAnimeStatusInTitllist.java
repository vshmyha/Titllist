package com.lerkin.titllist.controller.command.update;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Status;
import com.lerkin.titllist.entity_db.User;
import com.lerkin.titllist.entity_db.UserAnime;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.user_anime.UserAnimeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeAnimeStatusInTitllist implements Command {

    private final UserAnimeService userAnimeService = ServiceFactory.getUserAnimeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        String statusName = req.getParameter("animeStatus");
        Status status = Status.byText(statusName);
        Integer animeId = Integer.valueOf(req.getParameter("animeId"));
        userAnimeService.checkCurrentAnimeStatus(animeId, userId, status);
        userAnimeService.changeAnimeStatusInTitllist(animeId, userId, status);
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
