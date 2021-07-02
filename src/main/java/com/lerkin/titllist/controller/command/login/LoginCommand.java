package com.lerkin.titllist.controller.command.login;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.tool.ForwardUtil;
import com.lerkin.titllist.entity_db.Role;
import com.lerkin.titllist.entity_db.User;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password, null);
        user = userService.getUserByNameAndPass(user);

        if (user != null) {
            Role role = user.getRole();
            if (!role.isBlocked()) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                ForwardUtil.forwardToMainPage(req, resp);
            } else {
                ForwardUtil.forwardToBlockedPage(req, resp);
            }
        } else {
            req.setAttribute("response", "Wrong login or password");
            ForwardUtil.forwardToStartPage(req, resp);
        }
    }

    @Override
    public boolean roleUpdateRequired() {
        return false;
    }
}
