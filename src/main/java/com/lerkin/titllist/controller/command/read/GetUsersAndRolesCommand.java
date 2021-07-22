package com.lerkin.titllist.controller.command.read;

import com.lerkin.titllist.controller.CommandNames;
import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.controller.tool.ForwardUtil;
import com.lerkin.titllist.dao.entity.Role;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetUsersAndRolesCommand implements Command {
    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Role role = user.getRole();
        if (!role.isSimple()) {
            List<User> users = userService.getUsersAndRoles();
            ResponseEntity responseEntity = new ResponseEntity(ResponseType.OK, users);
            ResponseUtil.sendResponse(resp, responseEntity);
        } else {
            String url = ForwardUtil.createCommandPath(CommandNames.GO_TO_START_PAGE, null);
            ResponseEntity responseEntity = new ResponseEntity(ResponseType.NEW_PAGE, url);
            ResponseUtil.sendResponse(resp, responseEntity);
        }
    }
}
