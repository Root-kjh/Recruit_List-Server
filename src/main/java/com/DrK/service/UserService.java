package com.DrK.service;

import java.util.List;

import com.DrK.DTO.CompanyInfoDTO;
import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.DTO.UpdateUserDTO;
import com.DrK.DTO.UserinfoDTO;
import com.DrK.Errors.LoginFailedException;
import com.DrK.Errors.UserExistException;

public interface UserService {
	
	public UserinfoDTO signin(SigninDTO signinDTO) throws LoginFailedException;
	
	public boolean signup(SignupDTO signupDTO) throws UserExistException;
	
	public UserinfoDTO getUserinfo(Long userIdx);

	public boolean withdraw(Long userIdx);

	public UserinfoDTO editUserinfo(Long userIdx, UpdateUserDTO updateUserDTO);

	public boolean editPassword(Long userIdx, String password);

	public List<CompanyInfoDTO> getUserLikeCompany(Long userIdx);
	
	public boolean setLikeCompany(Long userIdx, String companyId);
	
	public boolean deleteLikeCompany(Long userIdx, String companyId);
}
