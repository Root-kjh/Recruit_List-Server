package com.DrK.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrK.entities.User;
import com.DrK.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class UserServicelmpl implements UserService{
	
	@Setter(onMethod_ = {@Autowired})
	private UserRepository userRepository;

	@Override
	public String createToken(String username,String password) {
		User user=userRepository.findByName(username);
		System.out.println(user.getName());
		long curTime=System.currentTimeMillis();
		if(user.getPassword().equals(password))
		return Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setExpiration(new Date(curTime+36000000))
				.setIssuedAt(new Date(curTime))
				.claim("user", user.getName())
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact();
		else
			return "fail";
	}
	
	private byte[] generateKey() {
		byte[] key=null;
		try {
			key="DrK".getBytes("UTF-8");
		} catch (Exception e) {
			log.error(e);
		}
		return key;
	}

	@Override
	public boolean Signup(User user) {
		try {
			if (userRepository.findByName(user.getName())==null) {
				userRepository.save(user);
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
