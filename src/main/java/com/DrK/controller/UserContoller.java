package com.DrK.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrK.Entities.Company;
import com.DrK.Entities.User;
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
	public List<Company> list(@RequestHeader(value="jwt",required=true)String jwt) {
		try {
			return UserService.getUserLikeCompany(jwt);
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(path="/company/{companyId}",method = RequestMethod.PUT)
	public boolean setCompany(
			@RequestHeader(value="jwt",required=true)String jwt,
			@PathVariable("companyId") String companyId) {
		try {
			String username=UserService.getUserName(jwt);
			UserService.setLikeCompany(username, companyId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping(path="/company/{companyId}",method = RequestMethod.DELETE)
	public boolean delCompany(
			@RequestHeader(value="jwt",required=true)String jwt,
			@PathVariable("companyId") String companyId) {
		try {
			String username=UserService.getUserName(jwt);
			UserService.deleteLikeCompany(username, companyId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
