package com.DrK.Exceptions;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BusinessException extends Exception{
    
    private static final long serialVersionUID = 1L;

    private static String requestToString(HttpServletRequest request) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(request.getParameterMap());
            return requestBody;
        } catch(JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    public BusinessException() {
    }

    public BusinessException(String info, HttpServletRequest request, String url) {
        super(info + "Request[" + requestToString(request) + "]" + "URL[" + url + "]");
    }
}