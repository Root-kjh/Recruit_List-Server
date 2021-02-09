package com.DrK.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.DrK.config.UrlConfig;
import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Entities.UserEntity;
import com.DrK.Exceptions.RequestDataInvalidException;
import com.DrK.Exceptions.UserDataInvalidException;
import com.DrK.Exceptions.UserExistException;
import com.DrK.service.UserService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserContoller {
	
	@Autowired
	private UserService UserService;
	
	@RequestMapping(path = UrlConfig.User.signin, method = RequestMethod.POST)
	public String signin(HttpServletRequest request, @Valid @RequestBody SigninDTO signinDTO, Errors errors)
		throws RequestDataInvalidException, UserDataInvalidException{
		if(errors.hasErrors())
			throw new RequestDataInvalidException("RequestDataInvalid", request, UrlConfig.User.signin);
		try{
			String token= UserService.createToken(signinDTO);
			return token;
		} catch (UserDataInvalidException e) {
			e.printStackTrace();
			throw new UserDataInvalidException("Login Failed", request, UrlConfig.User.signin);
		}
	}
	
	@RequestMapping(path=UrlConfig.User.signup ,method = RequestMethod.POST)
	public boolean signup(HttpServletRequest request, @Valid @RequestBody SignupDTO signupDTO, Errors errors)
		throws RequestDataInvalidException, UserExistException {
		if(errors.hasErrors())
			throw new RequestDataInvalidException("RequestDataInvalid", request, UrlConfig.User.signup);
		try{
			return UserService.Signup(signupDTO);
		} catch (UserExistException e){
			e.printStackTrace();
			throw new UserExistException("Exist User", request, UrlConfig.User.signup);
		}
	}
	
	@RequestMapping(path=UrlConfig.User.getLikeCompany , method = RequestMethod.GET)
	public List<CompanyEntity> getLikeCompany(Authentication authentication) {
		try {
			authentication.getPrincipal();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path=UrlConfig.User.addLikeCompany ,method = RequestMethod.PUT)
	public boolean addLikeCompany(
			Authentication authentication,
			@RequestParam String companyId) {
		try {
			Long userIdx = ((UserEntity) authentication.getPrincipal()).getIdx();
			return UserService.setLikeCompany(userIdx, companyId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(path=UrlConfig.User.deleteLikeCompany ,method = RequestMethod.DELETE)
	public boolean delCompany(
			Authentication authentication,
			@RequestParam String companyId) {
		try {
			String username= ((UserEntity) authentication.getPrincipal()).getName();
			return UserService.deleteLikeCompany(username, companyId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
