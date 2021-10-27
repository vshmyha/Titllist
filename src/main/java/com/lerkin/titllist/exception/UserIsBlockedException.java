package com.lerkin.titllist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserIsBlockedException extends UserFriendlyException {

	private static final String YOU_ARE_BLOCKED = "You are blocked";

	public UserIsBlockedException() {

		super(YOU_ARE_BLOCKED);
	}
}
