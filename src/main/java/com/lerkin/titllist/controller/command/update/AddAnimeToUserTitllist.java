package com.lerkin.titllist.controller.command.update;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.anime.AnimeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddAnimeToUserTitllist implements Command {

    AnimeService animeService = ServiceFactory.getAnimeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String statusName = req.getParameter("animeStatus");
        Status status = Status.byText(statusName);
        Integer animeId = Integer.valueOf(req.getParameter("animeId"));
        animeService.addAnimeToUserTitllist(user, animeId, status);
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
