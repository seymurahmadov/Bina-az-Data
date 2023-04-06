package com.bina.az.binaazdata.exception;

import com.bina.az.binaazdata.model.CustomErrorResponse;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorResponse handleNotFoundException(NotFoundException ex) {
        return new CustomErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
