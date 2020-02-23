package com.DrK.service;

import java.util.List;

import com.DrK.entities.Company;
import com.DrK.entities.User;

public interface UserService {
	
	public String createToken(String username,String password);
	
	public boolean Signup(User user);
	
	public boolean isLoginUser(String jwt);
	
	public List<Company> getUserLikeCompany(String username);
	
	public boolean setLikeCompany(String username, String companyId);
	
	public boolean deleteLikeCompany(String username, String companyId);
}
