package com.DrK.Service;

import java.util.List;

import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.Entities.Company;

public interface UserService {
	
	public String createToken(SigninDTO signinDTO);
	
	public boolean Signup(SignupDTO signupDTO);
	
	public List<Company> getUserLikeCompany(String userName) throws Exception;
	
	public boolean setLikeCompany(String userName, String companyId) throws Exception;
	
	public boolean deleteLikeCompany(String userName, String companyId) throws Exception;
}
