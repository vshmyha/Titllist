package com.lerkin.titllist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends InternalTitllistException {

	private static final String UNAUTHORIZED = "Unauthorized";

	public UnauthorizedException() {

		super(UNAUTHORIZED);
	}
}
