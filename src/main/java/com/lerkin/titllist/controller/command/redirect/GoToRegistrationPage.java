package com.lerkin.titllist.controller.command.redirect;

import com.lerkin.titllist.controller.command.Command;
import com.lerkin.titllist.controller.tool.ForwardUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToRegistrationPage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        ForwardUtil.forwardToRegistrationPage(req, resp);
    }
}
