package com.DrK.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	@GetMapping("/login")
	public String login(@RequestParam(value="error",required = false)String error,
			@RequestParam(value="logout",required = false)String logout) {
		if(error!=null) 
			return "error";
		if(logout!=null)
			return "logout";
		return "login";
	}
}
