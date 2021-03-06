package com.lerkin.titllist.controller.command.read;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.dao.entity.Genre;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.genre.GenreService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllGenresCommand implements Command {
    private final GenreService genreService = ServiceFactory.getGenreService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Genre> genres = genreService.getGenres();
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, genres);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
