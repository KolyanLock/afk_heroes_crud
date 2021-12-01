package com.kolyanlock.afk_heroes_crud.configuration;

import com.kolyanlock.afk_heroes_crud.exception.InvalidHeaderException;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RESTHandlerInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String auth = request.getHeader("Auth");
        if (!StringUtils.hasText(auth)) {
            throw new InvalidHeaderException("Go away!");
        }
        if (!auth.equals("djLdf1sdlf2fsdf2sdfdsf")) {
            throw new InvalidHeaderException("Go away!");
        }
        return true;
    }
}
