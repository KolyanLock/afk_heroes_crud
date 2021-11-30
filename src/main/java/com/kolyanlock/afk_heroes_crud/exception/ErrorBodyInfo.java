package com.kolyanlock.afk_heroes_crud.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorBodyInfo {

    private LocalDateTime timestamp;

    private HttpStatus status;

    private String error;

    private String exception;

    private String message;

    private String path;
}
