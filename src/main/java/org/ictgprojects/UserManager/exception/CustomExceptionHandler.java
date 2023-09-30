package org.ictgprojects.UserManager.exception;

import org.ictgprojects.UserManager.dto.AppUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex){

        return ResponseEntity.badRequest().body(new AppUserResponse(ex.getStatus(), ex.getResponseCode(),
                ex.getResponseMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> onHandleException(Exception ex){

        return ResponseEntity.badRequest().body(new AppUserResponse("FAILED", "99",
                ex.getMessage(), null));
    }
}
