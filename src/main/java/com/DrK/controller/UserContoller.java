package com.DrK.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DrK.Config.UrlConfig;
import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.Entities.Company;
import com.DrK.Service.UserService;

import lombok.Setter;

@RestController
public class UserContoller {
	
	@Setter(onMethod_ = {@Autowired})
	private UserService UserService;
	
	@RequestMapping(path = UrlConfig.User.signin, method = RequestMethod.POST)
	public String signin(@Valid @RequestBody SigninDTO signinDTO) {
		String token= UserService.createToken(signinDTO);
		if (token.equals("fail")){
			return "false";
		}else {
			return token;
		}
	}
	
	@RequestMapping(path=UrlConfig.User.signup ,method = RequestMethod.POST)
	public boolean signup(@Valid @RequestBody SignupDTO signupDTO) {
		return UserService.Signup(signupDTO);
	}
	
	@RequestMapping(path=UrlConfig.User.getLikeCompany , method = RequestMethod.GET)
	public List<Company> getLikeCompany(Authentication authentication) {
		try {
			// authentication.getPrincipal();
			// return UserService.getUserLikeCompany();
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(path=UrlConfig.User.addLikeCompany ,method = RequestMethod.PUT)
	public boolean addLikeCompany(
			Authentication authentication,
			@RequestParam String companyId) {
		try {
			// UserService.setLikeCompany(username, companyId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping(path=UrlConfig.User.deleteLikeCompany ,method = RequestMethod.DELETE)
	public boolean delCompany(
			Authentication authentication,
			@RequestParam String companyId) {
		try {
			// UserService.deleteLikeCompany(username, companyId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
