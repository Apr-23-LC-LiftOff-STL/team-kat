package org.launchcode.TasteBuddiesServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventDoesNotExistException extends RuntimeException {
    public EventDoesNotExistException(String message){
        super(message);
    }
}
