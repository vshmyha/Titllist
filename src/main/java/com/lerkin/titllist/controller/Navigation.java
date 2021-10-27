package com.lerkin.titllist.controller;

public interface Navigation {

	// Parameters
	String ID_PARAM = "id";
	String ID_PATH_PARAM = "{" + ID_PARAM + "}";
	String STATUS_PARAM = "status";
	String GENRES_PARAM = "genres";
	String TYPE_PARAM = "type";

	// Controllers
	String SEARCH = "/search";
	String ROLE = "/role";
	String USER = "/user";
	String ANIME = "/anime";
	String COMPONENT = "/component";
	String ANIME_COMPONENT = ANIME + COMPONENT;
	String GENRE = "/genre";
	String TITLLIST = "/titllist";

	// Endpoints
	String CHANGE = "/change";
	String REGISTRATION = "/registration";
	String LOGIN = "/login";
	String LOGOUT = "/logout";
	String STATUS = "/status";
	String ADD = "/add";
	String DATE = "/date";
	String TYPE = "/type";
	String SLASH = "/";
	String CHECK = "/check";
	String START = "/start_page";
	String REGISTRATION_PAGE = "/registration_page";
	String MAIN = "/main_page";
	String BLOCKED = "/blocked_page";
	String BY_ID = "/" + ID_PATH_PARAM;
}
