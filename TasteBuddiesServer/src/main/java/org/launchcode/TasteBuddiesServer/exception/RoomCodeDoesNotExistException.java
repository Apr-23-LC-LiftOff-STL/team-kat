package org.launchcode.TasteBuddiesServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomCodeDoesNotExistException extends RuntimeException{
    public RoomCodeDoesNotExistException(String message){
        super(message);
    }
}
