package com.app.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleAllException(
      Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse(ex, request);
        return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ExceptionResponse getExceptionResponse(Exception ex, WebRequest request) {
        return new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false));
    }


    @ExceptionHandler(value = { ResourceNotFound.class})
    protected ResponseEntity<Object> handleResourceNotFoundException(
            Exception ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        ExceptionResponse exceptionResponse = getExceptionResponse(ex, request);
        return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
    }
}