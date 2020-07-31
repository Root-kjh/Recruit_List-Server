package com.DrK.Service;

import java.util.List;

import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.DTO.UserinfoDTO;
import com.DrK.Entities.CompanyEntity;

public interface UserService {
	
	public String createToken(SigninDTO signinDTO);
	
	public boolean Signup(SignupDTO signupDTO);
	
	public UserinfoDTO getUserinfo(String userName);

	public boolean withdraw(String userName, String password);

	public String editUserinfo(String userName, UserinfoDTO userinfoDTO) throws Exception;

	public boolean editPassword(String userName, String newPassword);

	public List<CompanyEntity> getUserLikeCompany(String userName) throws Exception;
	
	public boolean setLikeCompany(String userName, String companyId) throws Exception;
	
	public boolean deleteLikeCompany(String userName, String companyId) throws Exception;
}
