package org.launchcode.TasteBuddiesServer.controllers;

import org.launchcode.TasteBuddiesServer.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@ControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserAlreadyJoinedEventException.class)
    public ResponseEntity<Object> handleUserAlreadyJoinedEventException(UserAlreadyJoinedEventException UAJEE){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, UAJEE.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
    @ExceptionHandler(RoomCodeDoesNotExistException.class)
    public ResponseEntity<Object> handleRoomCodeDoesNotExistException(RoomCodeDoesNotExistException RCDNEE){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, RCDNEE.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException UNFE) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, UNFE.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(EventDoesNotExistException.class)
    public ResponseEntity<Object> handleEventDoesNotExistException(EventDoesNotExistException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
