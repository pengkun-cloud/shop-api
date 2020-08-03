package com.fh.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandleException {
    @ExceptionHandler(MyException.class)
    public ServerResponse handleMyException(Exception e){
        e.printStackTrace();
        return ServerResponse.loginError();
    }
}
