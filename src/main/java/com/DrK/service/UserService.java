package com.DrK.service;

import java.util.List;

import com.DrK.Entities.Company;
import com.DrK.Entities.User;

public interface UserService {
	
	public String createToken(String username,String password);
	
	public boolean Signup(User user);
	
	public List<Company> getUserLikeCompany(String jwt) throws Exception;
	
	public boolean setLikeCompany(String username, String companyId) throws Exception;
	
	public boolean deleteLikeCompany(String username, String companyId) throws Exception;
	
	public String getUserName(String jwt) throws Exception;
}
