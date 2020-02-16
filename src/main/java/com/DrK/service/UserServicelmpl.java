package com.DrK.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrK.entities.User;
import com.DrK.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
public class UserServicelmpl implements UserService{
	
	@Setter(onMethod_ = {@Autowired})
	private UserRepository userRepository;

	@Override
	public List<User> getList() {
		return userRepository.findAll();
	}

}
