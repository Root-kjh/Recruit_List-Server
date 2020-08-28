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
import com.DrK.Exceptions.UserDataInvalidException;
import com.DrK.Exceptions.UserExistException;
import com.DrK.Repositories.CompanyRepository;
import com.DrK.Repositories.UserLikeCompanyRepository;
import com.DrK.Repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

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
	public String createToken(SigninDTO signinDTO) throws UserDataInvalidException{
		UserEntity userEntity = userRepository.findByName(signinDTO.getUserName());
		if (userEntity != null && passwordEncoder.matches(signinDTO.getPassword(), userEntity.getPassword()))
			return jwtTokenProvider.createToken(signinDTO.getUserName());
		else
			throw new UserDataInvalidException();
	}

	@Override
	public boolean Signup(SignupDTO signupDTO) throws UserExistException{
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
				throw new UserExistException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CompanyEntity> getUserLikeCompany(String userName) {
		try{
			UserEntity userEntity = userRepository.findByName(userName);
			List<String> companyIdxs = new ArrayList<String>();
			for (UserLikeCompanyEntity userLikeCompany : UserLikeCompanyRepository.findByUserIdx(userEntity.getIdx())) {
				companyIdxs.add(userLikeCompany.getCompanyIdx());
		}
		return CompanyRepository.findByIdIn(companyIdxs);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean setLikeCompany(String userName, String companyId) {
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
	public boolean deleteLikeCompany(String username, String companyId) {
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
		try{
			UserinfoDTO userinfoDTO = new UserinfoDTO();
			UserEntity userEntity = userRepository.findByName(userName);
			userinfoDTO.setUserName(userEntity.getName());
			userinfoDTO.setEmail(userEntity.getEmail());
			userinfoDTO.setSignupDate(userEntity.getSignupDate());
			return userinfoDTO;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean withdraw(String userName, String password) throws UserDataInvalidException{
		UserEntity userenEntity = userRepository.findByName(userName);
		if (passwordEncoder.matches(password, userenEntity.getPassword())){
			userRepository.delete(userenEntity);
			return true;
		}
		throw new UserDataInvalidException();
	}

	@Override
	@Transactional
	public String editUserinfo(String userName, UserinfoDTO userinfoDTO) throws UserExistException{
		UserEntity userEntity = userRepository.findByName(userName);
		if(!userEntity.getName().equals(userinfoDTO.getUserName())){
			if (userRepository.isExistUser(userinfoDTO.getUserName()))
				throw new UserExistException();
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
