package com.lerkin.titllist.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	void execute(HttpServletRequest req, HttpServletResponse resp);

	default boolean roleUpdateRequired() {

		return true;
	}

}
