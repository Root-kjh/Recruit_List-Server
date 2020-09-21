package com.DrK.service;

import java.util.List;

import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.DTO.UserinfoDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Exceptions.UserDataInvalidException;
import com.DrK.Exceptions.UserExistException;

public interface UserService {
	
	public String createToken(SigninDTO signinDTO) throws UserDataInvalidException;
	
	public boolean Signup(SignupDTO signupDTO) throws UserExistException;
	
	public UserinfoDTO getUserinfo(String userName);

	public boolean withdraw(String userName, String password) throws UserDataInvalidException;

	public String editUserinfo(String userName, UserinfoDTO userinfoDTO) throws UserExistException;

	public boolean editPassword(String userName, String newPassword);

	public List<CompanyEntity> getUserLikeCompany(String userName);
	
	public boolean setLikeCompany(String userName, String companyId);
	
	public boolean deleteLikeCompany(String userName, String companyId);
}
