package com.kolyanlock.afk_heroes_crud.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorBodyInfo {

    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String exception;

    private String message;

    private String path;
}
