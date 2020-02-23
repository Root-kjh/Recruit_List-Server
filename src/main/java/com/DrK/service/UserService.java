package com.DrK.service;

import com.DrK.entities.User;

public interface UserService {
	
	public String createToken(String username,String password);
	
	public boolean Signup(User user);
}
