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
    String GET_ALL_RELEASE_DATES_COMMAND = "getAllReleaseDatesCommand";
    String CHANGE_PASSWORD_COMMAND = "changePasswordCommand";
    String GET_BY_TYPE_COMMAND = "getByTypeCommand";
    String GET_BY_GENRE_COMMAND = "getByGenreCommand";
    String GET_BY_RELEASE_DATE_COMMAND =  "getByReleaseDateCommand";
    String GET_ALL_ANIME = "getAllAnime";
    String ADD_NEW_ANIME = "addNewAnime";
    String REFRESH_AUTHORITIES = "refreshAuth";
    String GO_TO_BLOCKED_PAGE = "goToBlockedPage";
    String GET_USERS_AND_ROLES = "getUsersAndRoles";
    String GET_ROLES_COMMAND = "getRolesCommand";
}
