package todo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import todo.domain.InvalidUserException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity invalidUser(InvalidUserException toHandle){
        return new ResponseEntity(toHandle.getMessage(), HttpStatus.FORBIDDEN);
    }



}
