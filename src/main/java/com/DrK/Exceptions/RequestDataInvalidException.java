package com.DrK.Exceptions;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;

@Getter
public class RequestDataInvalidException extends BusinessException{

    private static final long serialVersionUID = 1L;
    final private String errorMessage = "RequestDataInvalid";
    final private int errorCode = 405;

    public RequestDataInvalidException(){}

    public RequestDataInvalidException(String info, HttpServletRequest request, String url){
        super(info, request, url);
    }
}