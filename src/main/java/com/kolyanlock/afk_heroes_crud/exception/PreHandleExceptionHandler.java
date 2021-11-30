package com.kolyanlock.afk_heroes_crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class PreHandleExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorBodyInfo> handleInvalidHeaderException(InvalidHeaderException ex, HttpServletRequest request) {
        ErrorBodyInfo body = new ErrorBodyInfo();
        body.setTimestamp(LocalDateTime.now());
        body.setStatus(HttpStatus.I_AM_A_TEAPOT);
        body.setError(HttpStatus.I_AM_A_TEAPOT.getReasonPhrase());
        body.setException(ex.getClass().getName());
        body.setMessage(ex.getMessage());
        body.setPath(request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.I_AM_A_TEAPOT);
    }
}
