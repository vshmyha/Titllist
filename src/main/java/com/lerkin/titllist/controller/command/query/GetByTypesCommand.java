package com.lerkin.titllist.controller.command.query;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.entity_db.Anime;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.query.type.TypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetByTypesCommand implements Command {
    private final TypeService typeService = ServiceFactory.getTypeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String query = req.getQueryString();
        String idType = query.substring(query.lastIndexOf('=') + 1);
        Integer id = Integer.parseInt(idType);
        List<Anime> animeByTypes = typeService.getByTypes(id);
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, animeByTypes);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
