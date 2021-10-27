package com.lerkin.titllist.controller;

public interface Navigation {

	// Parameters
	String ID_PARAM = "id";
	String ID_PATH_PARAM = "{" + ID_PARAM + "}";
	String STATUS_PARAM = "status";
	String GENRES_PARAM = "genres";
	String TYPE_PARAM = "type";

	// Controllers
	String LOGIN = "/login";
	String LOGOUT = "/logout";
	String REGISTRATION = "/registration";
	String SEARCH = "/search";
	String ROLE = "/role";
	String CHANGE = "/change";
	String USER_ROLES = "/user_roles";
	String ANIME = "/anime";
	String COMPONENT = "/component";
	String ANIME_COMPONENT = ANIME + COMPONENT;
	String GENRE = "/genre";
	String TITLLIST = "/titllist";

	// Endpoints
	String STATUS = "/status";
	String ADD = "/add";
	String DATE = "/date";
	String TYPE = "/type";
	String SLASH = "/";
	String START = "/start_page";
	String REGISTRATION_PAGE = "/registration_page";
	String MAIN = "/main_page";
	String BLOCKED = "/blocked_page";
	String BY_ID = "/" + ID_PATH_PARAM;
}
