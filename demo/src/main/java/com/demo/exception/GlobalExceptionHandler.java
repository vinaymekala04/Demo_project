package com.demo.exception;

import com.demo.model.ErrorResponce;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.apache.commons.lang3.exception.ExceptionUtils;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponce> handler(Exception e){

        ErrorResponce errorResponce = new ErrorResponce();

        String msg= ExceptionUtils.getRootCauseMessage(e);
        e.printStackTrace();
        errorResponce.setHttpCode(HttpStatus.NOT_FOUND.value());
        errorResponce.setMessage("Invalid HTTP request..."+msg);

        return new ResponseEntity<>(errorResponce,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomeException.class)
    public ResponseEntity<ErrorResponce> handler(CustomeException e){

        ErrorResponce errorResponce = new ErrorResponce();

        errorResponce.setHttpCode(HttpStatus.NOT_FOUND.value());
        errorResponce.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponce,HttpStatus.NOT_FOUND);
    }
}
