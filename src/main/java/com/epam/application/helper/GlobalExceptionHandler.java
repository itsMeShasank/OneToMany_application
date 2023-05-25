package com.epam.application.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFields(MethodArgumentNotValidException exception, WebRequest request) {
        StringBuilder message=new StringBuilder();
        exception.getAllErrors().forEach(error ->
                message.append(error.getDefaultMessage()).append("\n")
        );
        ExceptionResponse entityResponse = new ExceptionResponse(new Date().toString(),message.toString(), HttpStatus.BAD_REQUEST.name(), request.getDescription(false));
        return new ResponseEntity<>(entityResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InternException.class)
    public ResponseEntity<ExceptionResponse> handleIntern(InternException exception, WebRequest request) {

        ExceptionResponse entityResponse = new ExceptionResponse(new Date().toString(),exception.getMessage(), exception.getStatus().name(), request.getDescription(false));
        return new ResponseEntity<>(entityResponse,exception.getStatus());
    }

    @ExceptionHandler(value = BatchException.class)
    public ResponseEntity<ExceptionResponse> handleBatch(BatchException exception, WebRequest request) {

        ExceptionResponse entityResponse = new ExceptionResponse(new Date().toString(),exception.getMessage(), exception.getStatus().name(), request.getDescription(false));
        return new ResponseEntity<>(entityResponse,exception.getStatus());
    }

}
