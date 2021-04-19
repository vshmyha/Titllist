package com.lerkin.titllist.controller;

import com.lerkin.titllist.constant.Messages;
import com.lerkin.titllist.controller.command.*;
import com.lerkin.titllist.controller.command.login.LoginCommand;
import com.lerkin.titllist.controller.command.login.RegistrationCommand;
import com.lerkin.titllist.controller.command.redirect.GoToStartPage;
import com.lerkin.titllist.exception.UserFriendlyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

class TaskManager {

    private static final Map<String, Command> COMMAND_MAP = new HashMap<>();

    static {
        COMMAND_MAP.put("login", new LoginCommand());
        COMMAND_MAP.put("registration", new RegistrationCommand());
        COMMAND_MAP.put("goToStartPage", new GoToStartPage());
    }

    static void impl(String commandName, HttpServletRequest request, HttpServletResponse response) {
        Command command = COMMAND_MAP.get(commandName);
        if (command != null) {
            command.execute(request, response);
        } else {
            throw new UserFriendlyException(Messages.UNKNOWN_COMMAND);
        }
    }
}
