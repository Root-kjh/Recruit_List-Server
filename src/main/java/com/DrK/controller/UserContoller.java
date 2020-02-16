package com.DrK.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserContoller {
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value="error",required = false)String error,
			@RequestParam(value="logout",required = false)String logout) {
		if(error!=null) 
			return "error";
		if(logout!=null)
			return "logout";
		return "login";
	}
}
