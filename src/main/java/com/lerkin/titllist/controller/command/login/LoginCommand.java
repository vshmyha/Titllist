package com.lerkin.titllist.controller.command.login;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.tool.ForwardUtil;
import com.lerkin.titllist.entity_db.User;
import com.lerkin.titllist.service.ServiceFactory;
import com.lerkin.titllist.service.login.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private final UserService userService = ServiceFactory.getLoginService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password, null);
        user = userService.getUserByNameAndPass(user);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("username", user.getUserName());
            ForwardUtil.forwardToMainPage(req, resp);
        } else {
            req.setAttribute("response", "Wrong login or password");
            ForwardUtil.forwardToStartPage(req, resp);
        }
    }
}
