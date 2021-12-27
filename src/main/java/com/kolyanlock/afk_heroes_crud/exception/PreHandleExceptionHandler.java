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
        HttpStatus status = HttpStatus.I_AM_A_TEAPOT;

        ErrorBodyInfo body = new ErrorBodyInfo();
        body.setTimestamp(LocalDateTime.now());
        body.setStatus(status.value());
        body.setError(status.getReasonPhrase());
        body.setException(ex.getClass().getName());
        body.setMessage(ex.getMessage());
        body.setPath(request.getRequestURI());

        return new ResponseEntity<>(body, status);
    }
}
