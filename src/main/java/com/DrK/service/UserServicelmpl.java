package com.DrK.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DrK.entities.Company;
import com.DrK.entities.User;
import com.DrK.entities.UserLikeCompany;
import com.DrK.repositories.CompanyRepository;
import com.DrK.repositories.UserLikeCompanyRepository;
import com.DrK.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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

	@Setter(onMethod_ = {@Autowired})
	private UserLikeCompanyRepository UserLikeCompanyRepository;
	
	@Setter(onMethod_ = {@Autowired})
	private CompanyRepository CompanyRepository;
	
	@Override
	public String createToken(String username,String password) {
		User user=userRepository.findByName(username);
		long curTime=System.currentTimeMillis();
		if(user.getPassword().equals(password))
		return Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setExpiration(new Date(curTime+600000))
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

	@Override
	public List<Company> getUserLikeCompany(String jwt) throws Exception {
		Jws<Claims> cJws=Jwts.parser()
				.setSigningKey(generateKey())
				.parseClaimsJws(jwt);
		String username=(String) cJws.getBody().get("user");
		User user=userRepository.findByName(username);
		List<String> companyIdxs = new ArrayList<String>();
		for (UserLikeCompany userLikeCompany : UserLikeCompanyRepository.findByUserIdx(user.getIdx())) {
			companyIdxs.add(userLikeCompany.getCompanyIdx());
		}
		return CompanyRepository.findByIdIn(companyIdxs);
	}

	@Override
	public boolean setLikeCompany(String username, String companyId) throws Exception{
		try {
			User user=userRepository.findByName(username);
			UserLikeCompany userCompany=new UserLikeCompany();
			userCompany.setCompanyIdx(companyId);
			userCompany.setUserIdx(user.getIdx());
			UserLikeCompanyRepository.save(userCompany);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteLikeCompany(String username, String companyId) throws Exception{
		try {
			User user=userRepository.findByName(username);
			UserLikeCompanyRepository.deleteByUserIdxAndCompanyIdx(user.getIdx(), companyId);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String getUserName(String jwt) throws Exception{
		Jws<Claims> cJws=Jwts.parser()
				.setSigningKey(generateKey())
				.parseClaimsJws(jwt);
		return((String) cJws.getBody().get("user"));
	}

}
