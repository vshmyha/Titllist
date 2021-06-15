package com.lerkin.titllist.exception;

public class SessionInvalidException extends RuntimeException {

    public SessionInvalidException() {
    }

    public SessionInvalidException(String message) {
        super(message);
    }

    public SessionInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionInvalidException(Throwable cause) {
        super(cause);
    }
}
