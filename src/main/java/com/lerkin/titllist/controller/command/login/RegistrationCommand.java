package com.lerkin.titllist.controller.command.login;

import com.lerkin.titllist.controller.CommandNames;
import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.controller.tool.ForwardUtil;
import com.lerkin.titllist.dao.entity.User;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RegistrationCommand implements Command {

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password, null);
        userService.registration(user);
        HttpSession session = request.getSession();
        session.setAttribute("username", user.getUserName());
        String url = ForwardUtil.createCommandPath(CommandNames.GO_TO_MAIN_PAGE, null);
        ResponseEntity customResponse = new ResponseEntity(ResponseType.NEW_PAGE, url);
        ResponseUtil.sendResponse(response, customResponse);
    }

    @Override
    public boolean roleUpdateRequired() {
        return false;
    }
}
