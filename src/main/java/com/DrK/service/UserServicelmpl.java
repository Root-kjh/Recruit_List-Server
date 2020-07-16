package com.DrK.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DrK.Config.JWT.JwtTokenProvider;
import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.Entities.Company;
import com.DrK.Entities.User;
import com.DrK.Entities.UserLikeCompany;
import com.DrK.Repositories.CompanyRepository;
import com.DrK.Repositories.UserLikeCompanyRepository;
import com.DrK.Repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServicelmpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserLikeCompanyRepository UserLikeCompanyRepository;
	
	@Autowired
	private CompanyRepository CompanyRepository;
	
	private final JwtTokenProvider jwtTokenProvider;

	private final PasswordEncoder passwordEncoder;

	@Override
	public String createToken(SigninDTO signinDTO) {
		User user=userRepository.findByName(signinDTO.getUserName());
		if(user!=null && 
				passwordEncoder.matches(signinDTO.getPassword(), user.getPassword()))
			return jwtTokenProvider.createToken(signinDTO.getUserName());
		else
			return "fail";
	}

	@Override
	public boolean Signup(SignupDTO signupDTO) {
		try {
			User user = new User();
			user.setEmail(signupDTO.getEmail());
			user.setName(signupDTO.getUserName());
			user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
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
