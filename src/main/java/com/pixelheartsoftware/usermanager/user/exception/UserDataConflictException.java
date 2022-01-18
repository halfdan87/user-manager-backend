package com.pixelheartsoftware.usermanager.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserDataConflictException extends RuntimeException {
    public UserDataConflictException(String message) {
        super(message);
    }

    public UserDataConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
