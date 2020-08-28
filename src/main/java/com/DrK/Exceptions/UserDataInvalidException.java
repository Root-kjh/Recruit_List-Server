package com.DrK.Exceptions;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;

@Getter
public class UserDataInvalidException extends BusinessException {

    private static final long serialVersionUID = 1L;
    final private String errorMessage = "UserDataInvalid";
    final private int errorCode = 403;

    public UserDataInvalidException(){}

    public UserDataInvalidException(String info, HttpServletRequest request, String url){
        super(info, request, url);
    }
}