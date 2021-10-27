package com.lerkin.titllist.exception;

public class RequestedObjectNotFoundException extends InternalTitllistException {

	private static final String MESSAGE = "";

	public RequestedObjectNotFoundException(String objectType, Integer id) {

		super(String.format("%s type not found by %s id", objectType, id));
	}
}
