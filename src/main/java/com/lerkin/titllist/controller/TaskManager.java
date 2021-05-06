package com.lerkin.titllist.controller;

import com.lerkin.titllist.constant.Messages;
import com.lerkin.titllist.controller.command.*;
import com.lerkin.titllist.controller.command.login.ChangePasswordCommand;
import com.lerkin.titllist.controller.command.login.LoginCommand;
import com.lerkin.titllist.controller.command.login.LogoutCommand;
import com.lerkin.titllist.controller.command.login.RegistrationCommand;
import com.lerkin.titllist.controller.command.query.GetAllGenresCommand;
import com.lerkin.titllist.controller.command.query.GetAllReleaseDateSCommand;
import com.lerkin.titllist.controller.command.query.GetAllTypesCommand;
import com.lerkin.titllist.controller.command.query.type.*;
import com.lerkin.titllist.controller.command.redirect.GoToMainPage;
import com.lerkin.titllist.controller.command.redirect.GoToRegistrationPage;
import com.lerkin.titllist.controller.command.redirect.GoToStartPage;
import com.lerkin.titllist.exception.UserFriendlyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

class TaskManager {

    private static final Map<String, Command> COMMAND_MAP = new HashMap<>();

    static {
        COMMAND_MAP.put(CommandNames.LOGIN, new LoginCommand());
        COMMAND_MAP.put(CommandNames.REGISTRATION, new RegistrationCommand());
        COMMAND_MAP.put(CommandNames.GO_TO_START_PAGE, new GoToStartPage());
        COMMAND_MAP.put(CommandNames.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        COMMAND_MAP.put(CommandNames.GO_TO_MAIN_PAGE, new GoToMainPage());
        COMMAND_MAP.put(CommandNames.LOGOUT, new LogoutCommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_TYPES_COMMAND, new GetAllTypesCommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_GENRES_COMMAND, new GetAllGenresCommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_RELEASE_DATES_COMMAND, new GetAllReleaseDateSCommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_ANIME_FILM_COMMAND, new GetAllAnimeFilmCommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_ANIME_ONA_COMMAND, new GetAllAnimeONACommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_ANIME_OVA_COMMAND, new GetAllAnimeOVACommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_ANIME_SPESHL_COMMAND, new GetAllAnimeSpeshlCommand());
        COMMAND_MAP.put(CommandNames.GET_ALL_ANIME_TV_SERIAL_COMMAND, new GetAllAnimeTVSerialCommand());
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
