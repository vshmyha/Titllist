package com.lerkin.titllist.controller;

import com.lerkin.titllist.constant.Messages;
import com.lerkin.titllist.controller.command.*;
import com.lerkin.titllist.controller.command.login.ChangePasswordCommand;
import com.lerkin.titllist.controller.command.login.LoginCommand;
import com.lerkin.titllist.controller.command.login.LogoutCommand;
import com.lerkin.titllist.controller.command.login.RegistrationCommand;
import com.lerkin.titllist.controller.command.read.*;
import com.lerkin.titllist.controller.command.redirect.GoToBlockedPage;
import com.lerkin.titllist.controller.command.redirect.GoToMainPage;
import com.lerkin.titllist.controller.command.redirect.GoToRegistrationPage;
import com.lerkin.titllist.controller.command.redirect.GoToStartPage;
import com.lerkin.titllist.controller.command.update.AddNewAnime;
import com.lerkin.titllist.exception.UserFriendlyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

class TaskManager {

    private static final Map<String, Command> COMMAND_MAP = new HashMap<>();
    private static final UpdateAuthorityCommand AUTH_REFRESH_COMMAND = new UpdateAuthorityCommand();

    static {
        COMMAND_MAP.put(CommandNames.LOGIN, new LoginCommand());
        COMMAND_MAP.put(CommandNames.REGISTRATION, new RegistrationCommand());
        COMMAND_MAP.put(CommandNames.GO_TO_START_PAGE, new GoToStartPage());
        COMMAND_MAP.put(CommandNames.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        COMMAND_MAP.put(CommandNames.GO_TO_MAIN_PAGE, new GoToMainPage());
        COMMAND_MAP.put(CommandNames.LOGOUT, new LogoutCommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_TYPES_COMMAND, new GetAllTypesCommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_GENRES_COMMAND, new GetAllGenresCommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_RELEASE_DATES_COMMAND, new GetAllReleaseDatesCommand());
        COMMAND_MAP.put(CommandNames.CHANGE_PASSWORD_COMMAND, new ChangePasswordCommand());
        COMMAND_MAP.put(CommandNames.GET_BY_TYPE_COMMAND, new GetByTypeCommand());
        COMMAND_MAP.put(CommandNames.GET_BY_GENRE_COMMAND, new GetByGenreCommand());
        COMMAND_MAP.put(CommandNames.GET_BY_RELEASE_DATE_COMMAND, new GetByReleaseDateCommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_ANIME, new GetAllAnime());
        COMMAND_MAP.put(CommandNames.ADD_NEW_ANIME, new AddNewAnime());
        COMMAND_MAP.put(CommandNames.REFRESH_AUTHORITIES, AUTH_REFRESH_COMMAND);
        COMMAND_MAP.put(CommandNames.GO_TO_BLOCKED_PAGE, new GoToBlockedPage());
        COMMAND_MAP.put(CommandNames.GET_USERS_AND_ROLES, new GetUsersAndRolesCommand());
    }

    static void impl(String commandName, HttpServletRequest request, HttpServletResponse response) {
        Command command = COMMAND_MAP.get(commandName);
        if (command != null) {
            if (command.roleUpdateRequired()) {
                AUTH_REFRESH_COMMAND.execute(request, response);
            }
            command.execute(request, response);
        } else {
            throw new UserFriendlyException(Messages.UNKNOWN_COMMAND);
        }
    }
}
