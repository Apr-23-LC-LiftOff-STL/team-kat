package org.launchcode.TasteBuddiesServer.controllers;

import org.launchcode.TasteBuddiesServer.exception.ErrorResponse;
import org.launchcode.TasteBuddiesServer.exception.RoomCodeDoesNotExistException;
import org.launchcode.TasteBuddiesServer.exception.UserAlreadyJoinedEventException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
}
