package com.lerkin.titllist.controller.command.read;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.status.StatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAnimeStatusCommand implements Command {

    private final StatusService statusService = ServiceFactory.getStatusService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Status> statuses = statusService.getStatuses();
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, statuses);
        ResponseUtil.sendResponse(resp, responseEntity);

    }
}
