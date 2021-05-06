package com.lerkin.titllist.controller.command.query;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.query.type.TypeService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllTypesCommand implements Command {
    private final TypeService typeService = ServiceFactory.getTypeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        List<String> types = typeService.getTypes();
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, types);
        ResponseUtil.sendResponse(resp, responseEntity);
    }
}
