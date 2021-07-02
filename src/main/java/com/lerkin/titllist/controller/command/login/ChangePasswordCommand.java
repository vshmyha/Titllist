package com.lerkin.titllist.controller.command.login;

import com.lerkin.titllist.controller.CommandNames;
import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.response.ResponseEntity;
import com.lerkin.titllist.controller.response.ResponseType;
import com.lerkin.titllist.controller.response.ResponseUtil;
import com.lerkin.titllist.controller.tool.ForwardUtil;
import com.lerkin.titllist.entity_db.User;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePasswordCommand implements Command {
    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUserName();
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        User fullUser = new User(username, currentPassword, null);
        userService.checkCurrentPassword(fullUser);
        fullUser.setPassword(newPassword);
        userService.changePassword(fullUser);
        String url = ForwardUtil.createCommandPath(CommandNames.GO_TO_START_PAGE, null);
        ResponseEntity responseEntity = new ResponseEntity(ResponseType.NEW_PAGE, url);
        ResponseUtil.sendResponse(resp, responseEntity);

    }
}
