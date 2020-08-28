package com.DrK.Exceptions;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;

@Getter
public class UserExistException extends BusinessException{
    
    private static final long serialVersionUID = 1L;
    final private String errorMessage = "UserExist";
    final private int errorCode = 406;

    public UserExistException(){}

    public UserExistException(String info, HttpServletRequest request, String url){
        super(info, request, url);
    }   
}