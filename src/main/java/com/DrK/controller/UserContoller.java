package com.DrK.Controller;

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

import com.DrK.Config.UrlConfig;
import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.Entities.Company;
import com.DrK.Entities.User;
import com.DrK.Service.UserService;

import lombok.Setter;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserContoller {
	
	@Setter(onMethod_ = {@Autowired})
	private UserService UserService;
	
	@RequestMapping(path = UrlConfig.User.signin, method = RequestMethod.POST)
	public String signin(@RequestBody SigninDTO signinDTO) {
		String token= UserService.createToken(signinDTO.getUserName(), signinDTO.getPassword());
		if (token.equals("fail")){
			return "false";
		}else {
			return token;
		}
	}
	
	@RequestMapping(path=UrlConfig.User.signup ,method = RequestMethod.POST)
	public boolean signup(@RequestBody SignupDTO signupDTO) {
		return UserService.Signup(signupDTO);
	}
	
	@RequestMapping(path=UrlConfig.User.getLikeCompany , method = RequestMethod.GET)
	public List<Company> list(@RequestHeader(value="jwt",required=true)String jwt) {
		try {
			return UserService.getUserLikeCompany(jwt);
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(path=UrlConfig.User.addLikeCompany ,method = RequestMethod.PUT)
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
	
	@RequestMapping(path=UrlConfig.User.deleteLikeCompany ,method = RequestMethod.DELETE)
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
