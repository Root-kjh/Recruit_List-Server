package com.DrK.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DrK.entities.Company;
import com.DrK.entities.User;
import com.DrK.service.UserService;

import lombok.Setter;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserContoller {
	
	@Setter(onMethod_ = {@Autowired})
	private UserService UserService;
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@RequestBody Map<String,Object>params) {
		String token= UserService.createToken((String) params.get("username"), (String) params.get("password"));
		if (token.equals("fail")){
			return "false";
		}else {
			return token;
		}
	}
	
	@RequestMapping(path="/signup",method = RequestMethod.POST)
	public boolean signup(@RequestBody Map<String,Object>params) {
		User user=new User();
		user.setEmail((String) params.get("email"));
		user.setName((String) params.get("username"));
		user.setPassword((String) params.get("password"));
		user.setSignupDate(new Date());
		return UserService.Signup(user);
	}
	
	@RequestMapping(path="/company", method = RequestMethod.GET)
	public List<Company> list() {
		
		return companyService.getList();
	}
}
