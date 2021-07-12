package com.lerkin.titllist.controller.command.read;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.entity_db.Status;
import com.lerkin.titllist.entity_db.User;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.anime.AnimeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowUserTitllistCommand implements Command {

    private final AnimeService animeService = ServiceFactory.getAnimeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String statusName = req.getParameter("status");
        List<Anime> animes;
        if (statusName == null) {
            animes = animeService.getAnimeFromUserTitllist(user);
        } else {
            if(statusName.startsWith("In")){
                statusName = "In process";
            }
            Status status = Status.byText(statusName);
            animes = animeService.getAnimeFromUserTitllistByStatus(user, status);
        }
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, animes);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
