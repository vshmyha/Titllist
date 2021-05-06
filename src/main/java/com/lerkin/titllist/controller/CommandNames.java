package com.lerkin.titllist.controller;

public interface CommandNames {
    String LOGIN = "login";
    String REGISTRATION = "registration";
    String GO_TO_START_PAGE = "goToStartPage";
    String GO_TO_REGISTRATION_PAGE = "goToRegistrationPage";
    String GO_TO_MAIN_PAGE = "goToMainPage";
    String LOGOUT = "logout";
    String GET_ALL_TYPES_COMMAND = "getAllTypesCommand";
    String GET_ALL_GENRES_COMMAND = "getAllGenresCommand";
    String GET_ALL_RELEASE_DATES_COMMAND = "getAllReleaseDateCommand";
    String CHANGE_PASSWORD_COMMAND = "changePasswordCommand";
    String GET_BY_TYPES_COMMAND = "getByTypesCommand";
    String GET_BY_GENRES_COMMAND = "getByGenresCommand";
}
