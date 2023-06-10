package com.neon.jwtsecurityapp.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = {
    "com.neon.jwtsecurityapp.controller"
})
public class AppExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentInvalidException(MethodArgumentNotValidException exception) {
        HashMap<String, Object> map = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError ->
            map.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        return map;
    }

}
