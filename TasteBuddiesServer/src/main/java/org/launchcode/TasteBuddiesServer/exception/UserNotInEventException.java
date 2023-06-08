package org.launchcode.TasteBuddiesServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UserNotInEventException extends RuntimeException {
    public UserNotInEventException(String message){
        super(message);
    }
}