package com.lerkin.titllist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessDeniedException extends InternalTitllistException {

	private static final String ACCESS_DENIED = "Access denied!!!";

	public AccessDeniedException() {

		super(ACCESS_DENIED);
	}
}
