package com.DrK.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserContoller {
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, @RequestParam(value="error",required = false)String error,
			@RequestParam(value="logout",required = false)String logout) {
		if(error!=null) 
			return "error";
		if(logout!=null)
			return "logout";
		CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
		return token.getToken();
	}
	
	@RequestMapping(path="/csrf-token", method = RequestMethod.GET)
	public @ResponseBody String getCsrf(HttpServletRequest request) {
		CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
		return token.getToken();
	}
}
