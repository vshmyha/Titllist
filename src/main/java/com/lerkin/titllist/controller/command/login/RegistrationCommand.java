package com.lerkin.titllist.controller.command.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lerkin.titllist.controller.CommandNames;
import com.lerkin.titllist.controller.command.Command;

import com.lerkin.titllist.controller.response.CustomResponse;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.controller.tool.ForwardUtil;
import com.lerkin.titllist.entity_db.User;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.login.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RegistrationCommand implements Command {

    private final UserService userService = ServiceFactory.getLoginService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password, null);
        userService.registration(user);
        HttpSession session = request.getSession();
        session.setAttribute("username", user.getUserName());
        String command = CommandNames.GO_TO_MAIN_PAGE;
        String url = ForwardUtil.createCommandPath(command, null);
        CustomResponse customResponse = new CustomResponse(ResponseType.NEW_PAGE, url);
        try {
            String jsonResponse = ResponseUtil.createJSONResponse(customResponse);
            ResponseUtil.sendResponse(response, jsonResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
