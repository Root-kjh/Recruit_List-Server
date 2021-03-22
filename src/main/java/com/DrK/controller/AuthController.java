package com.DrK.controller;

import javax.validation.Valid;

import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.DTO.UserinfoDTO;
import com.DrK.Errors.RequestDataInvalidException;
import com.DrK.service.UserService;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@RequestMapping("/auth")
@RestController
public class AuthController {
	
	private final UserService UserService;
	
	@PostMapping("/signin")
    public UserinfoDTO signin( @RequestBody @Valid SigninDTO signinDTO, Errors errors)
		throws Exception{
		if(errors.hasErrors())
			throw new RequestDataInvalidException();
        return UserService.signin(signinDTO);
	}
	
	@PostMapping("/signup")
	public boolean signup(@RequestBody @Valid SignupDTO signupDTO, Errors errors)
		throws Exception {
		if(errors.hasErrors())
			throw new RequestDataInvalidException();
        UserService.signup(signupDTO);
        return true;
	}
}
