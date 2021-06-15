package com.lerkin.titllist.controller.command.read;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.release_date.ReleaseDateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllReleaseDatesCommand implements Command {
    ReleaseDateService releaseDateService = ServiceFactory.getReleaseDateService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Short> releaseDates = releaseDateService.getReleaseDate();
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, releaseDates);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
