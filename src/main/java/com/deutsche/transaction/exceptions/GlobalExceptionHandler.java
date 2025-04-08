package com.deutsche.transaction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.deutsche.transaction.entities.ErrorDetail;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Handle specific exception - InternalGlobalException
    @ExceptionHandler(InternalGlobalException.class)
    public ResponseEntity<ErrorDetail> handleAccountException(InternalGlobalException exception,
                                                                          WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            "NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    //Handle Generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleAccountException(Exception exception,
                                                              WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
