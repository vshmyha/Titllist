package com.lerkin.titllist.rest_controller;

public interface Navigation {

    // Controllers
    String LOGIN = "/login";
    String LOGOUT = "/logout";
    String REGISTRATION = "/registration";
    String ADD = "/add";
    String STATUS = "/status";
    String SEARCH = "/search";
    String ROLE = "/role";
    String CHANGE = "/change";
    String USER_ROLES = "/user_roles";
    // Endpoints
    String SLASH = "/";
    String TYPE = "/type";
    String DATE = "/date";
    String GENRE = "/genre";
    String TITLLIST = "/titllist";
    String ANIME = "/anime";
    String START = "/start_page";
    String REGISTRATION_PAGE = "/registration_page";
    String MAIN = "/main_page";
    String BLOCKED = "/blocked_page";
    // Parameters
    String ID_PARAM = "/{id}";
    String VALUE = "/{value}";
    String STATUS_PARAM = "/{status}";
}
