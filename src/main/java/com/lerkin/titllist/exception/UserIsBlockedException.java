package com.lerkin.titllist.exception;

public class UserIsBlockedException extends RuntimeException {

    public UserIsBlockedException() {
    }

    public UserIsBlockedException(String message) {
        super(message);
    }

    public UserIsBlockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsBlockedException(Throwable cause) {
        super(cause);
    }
}
