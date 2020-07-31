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
import com.DrK.DTO.UserinfoDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Entities.UserEntity;
import com.DrK.Entities.UserLikeCompanyEntity;
import com.DrK.Repositories.CompanyRepository;
import com.DrK.Repositories.UserLikeCompanyRepository;
import com.DrK.Repositories.UserRepository;

@Service
public class UserServicelmpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserLikeCompanyRepository UserLikeCompanyRepository;
	@Autowired
	private CompanyRepository CompanyRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String createToken(SigninDTO signinDTO) {
		UserEntity userEntity = userRepository.findByName(signinDTO.getUserName());
		if (userEntity != null && passwordEncoder.matches(signinDTO.getPassword(), userEntity.getPassword()))
			return jwtTokenProvider.createToken(signinDTO.getUserName());
		else
			return "fail";
	}

	@Override
	public boolean Signup(SignupDTO signupDTO) {
		try {
			UserEntity userEntity = new UserEntity();
			userEntity.setEmail(signupDTO.getEmail());
			userEntity.setName(signupDTO.getUserName());
			userEntity.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
			userEntity.setSignupDate(new Date());
			if (!userRepository.isExistUser(signupDTO.getUserName())) {
				userRepository.save(userEntity);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CompanyEntity> getUserLikeCompany(String userName) throws Exception {
		UserEntity userEntity = userRepository.findByName(userName);
		List<String> companyIdxs = new ArrayList<String>();
		for (UserLikeCompanyEntity userLikeCompany : UserLikeCompanyRepository.findByUserIdx(userEntity.getIdx())) {
			companyIdxs.add(userLikeCompany.getCompanyIdx());
		}
		return CompanyRepository.findByIdIn(companyIdxs);
	}

	@Override
	public boolean setLikeCompany(String userName, String companyId) throws Exception {
		try {
			UserEntity userEntity = userRepository.findByName(userName);
			UserLikeCompanyEntity userCompany = new UserLikeCompanyEntity();
			userCompany.setCompanyIdx(companyId);
			userCompany.setUserIdx(userEntity.getIdx());
			UserLikeCompanyRepository.save(userCompany);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteLikeCompany(String username, String companyId) throws Exception {
		try {
			UserEntity userEntity = userRepository.findByName(username);
			UserLikeCompanyRepository.deleteByUserIdxAndCompanyIdx(userEntity.getIdx(), companyId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public UserinfoDTO getUserinfo(String userName) {
		UserinfoDTO userinfoDTO = new UserinfoDTO();
		UserEntity userEntity = userRepository.findByName(userName);
		userinfoDTO.setUserName(userEntity.getName());
		userinfoDTO.setEmail(userEntity.getEmail());
		userinfoDTO.setSignupDate(userEntity.getSignupDate());
		return userinfoDTO;
	}

	@Override
	public boolean withdraw(String userName, String password) {
		UserEntity userenEntity = userRepository.findByName(userName);
		if (passwordEncoder.matches(password, userenEntity.getPassword())){
			userRepository.delete(userenEntity);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public String editUserinfo(String userName, UserinfoDTO userinfoDTO){
		UserEntity userEntity = userRepository.findByName(userName);
		if(!userEntity.getName().equals(userinfoDTO.getUserName())){
			if (userRepository.isExistUser(userinfoDTO.getUserName()))
				return "false";
			else
				userEntity.setName(userinfoDTO.getUserName());
		}
		userEntity.setEmail(userinfoDTO.getEmail());
		userRepository.save(userEntity);
		return jwtTokenProvider.createToken(userEntity.getName());	
	}

	@Override
	public boolean editPassword(String userName, String newPassword) {
		UserEntity userEntity = userRepository.findByName(userName);
		userEntity.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(userEntity);
		return true;
	}

}
