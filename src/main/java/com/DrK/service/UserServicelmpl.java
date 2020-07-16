package com.DrK.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DrK.Config.JWT.JwtTokenProvider;
import com.DrK.DTO.SignupDTO;
import com.DrK.Entities.Company;
import com.DrK.Entities.User;
import com.DrK.Entities.UserLikeCompany;
import com.DrK.Repositories.CompanyRepository;
import com.DrK.Repositories.UserLikeCompanyRepository;
import com.DrK.Repositories.UserRepository;

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
	
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public String createToken(String userName,String password) {
		User user=userRepository.findByName(userName);
		if(user.getPassword().equals(password))
			return jwtTokenProvider.createToken(userName);
		else
			return "fail";
	}

	@Override
	public boolean Signup(SignupDTO signupDTO) {
		try {
			User user = new User();
			user.setEmail(signupDTO.getEmail());
			user.setName(signupDTO.getUserName());
			user.setPassword(signupDTO.getPassword());
			user.setSignupDate(new Date());
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
	public List<Company> getUserLikeCompany(String userName) throws Exception {
		User user=userRepository.findByName(userName);
		List<String> companyIdxs = new ArrayList<String>();
		for (UserLikeCompany userLikeCompany : UserLikeCompanyRepository.findByUserIdx(user.getIdx())) {
			companyIdxs.add(userLikeCompany.getCompanyIdx());
		}
		return CompanyRepository.findByIdIn(companyIdxs);
	}

	@Override
	public boolean setLikeCompany(String userName, String companyId) throws Exception{
		try {
			User user=userRepository.findByName(userName);
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

}
