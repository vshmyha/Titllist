package com.lerkin.titllist.controller.response;

import com.lerkin.titllist.exception.UserFriendlyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handle(Exception ex) {
        if (ex instanceof UserFriendlyException) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
