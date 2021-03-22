package com.DrK.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import com.DrK.config.JWT.JwtTokenProvider;
import com.DrK.DTO.CompanyInfoDTO;
import com.DrK.DTO.CompanyInfoSiteDTO;
import com.DrK.DTO.RecruitNoticeDTO;
import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.DTO.UpdateUserDTO;
import com.DrK.DTO.UserinfoDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Entities.CompanyInfoEntity;
import com.DrK.Entities.RecruitmentNoticeEntity;
import com.DrK.Entities.UserEntity;
import com.DrK.Entities.UserLikeCompanyEntity;
import com.DrK.Errors.LoginFailedException;
import com.DrK.Errors.UserDataInvalidException;
import com.DrK.Errors.UserExistException;
import com.DrK.repositories.CompanyRepository;
import com.DrK.repositories.UserLikeCompanyRepository;
import com.DrK.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserLikeCompanyRepository UserLikeCompanyRepository;
	private final CompanyRepository CompanyRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public UserinfoDTO signin(SigninDTO signinDTO) throws LoginFailedException{
		UserEntity userEntity = userRepository.findByName(signinDTO.getUserName());
		if (userEntity != null && passwordEncoder.matches(signinDTO.getPassword(), userEntity.getPassword())){
			UserinfoDTO userinfoDTO = new UserinfoDTO();
			userinfoDTO.setEmail(userEntity.getEmail());
			userinfoDTO.setUserName(userEntity.getUsername());
			userinfoDTO.setJwt(jwtTokenProvider.createToken(signinDTO.getUserName()));
			return userinfoDTO;
		}else
			throw new LoginFailedException();
	}

	@Override
	@Transactional
	public boolean signup(SignupDTO signupDTO) throws UserExistException{
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(signupDTO.getEmail());
		userEntity.setName(signupDTO.getUserName());
		userEntity.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
		userEntity.setSignupDate(new Date());
		if (!userRepository.isExistUser(signupDTO.getUserName())) {
			userRepository.save(userEntity);
			return true;
		}else
			throw new UserExistException();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyInfoDTO> getUserLikeCompany(String userName) {
		UserEntity userEntity = userRepository.findByName(userName);
		List<CompanyInfoDTO> companyInfoDTOs = new ArrayList<CompanyInfoDTO>();
		for (UserLikeCompanyEntity userLikeCompany : UserLikeCompanyRepository.findByUserIdx(userEntity.getIdx())) {
			CompanyInfoDTO companyInfoDTO = new CompanyInfoDTO();
			CompanyEntity company = this.CompanyRepository.findById(userLikeCompany.getCompanyIdx()).get();
			companyInfoDTO.setCompanyName(company.getCompanyName());
			companyInfoDTO.setEmployeesNum(company.getEmployeesNum());
			companyInfoDTO.setFoundingYear(company.getFoundingYear());
			List<RecruitNoticeDTO> recruitNoticeDTOs = new ArrayList<RecruitNoticeDTO>();
			for (RecruitmentNoticeEntity recruitmentNoticeEntity : company.getRecruitmentNotice()) {
				RecruitNoticeDTO recruitNoticeDTO = new RecruitNoticeDTO();
				recruitNoticeDTO.setSiteName(recruitmentNoticeEntity.getSiteName());
				recruitNoticeDTO.setUri(recruitmentNoticeEntity.getUri());
				recruitNoticeDTOs.add(recruitNoticeDTO);
			}
			List<CompanyInfoSiteDTO> companyInfoSiteDTOs = new ArrayList<CompanyInfoSiteDTO>();
			for (CompanyInfoEntity companyInfoEntity: company.getCompanyInfo()) {
				CompanyInfoSiteDTO companyInfoSiteDTO = new CompanyInfoSiteDTO();
				companyInfoSiteDTO.setSiteName(companyInfoEntity.getSiteName());
				companyInfoSiteDTO.setUri(companyInfoEntity.getUri());
				companyInfoSiteDTOs.add(companyInfoSiteDTO);
			}
			companyInfoDTO.setRecruitNoticeDTOs(recruitNoticeDTOs);
			companyInfoDTO.setCompanyInfoSiteDTOs(companyInfoSiteDTOs);
			companyInfoDTOs.add(companyInfoDTO);
		}
		return companyInfoDTOs;
	}

	@Override
	@Transactional
	public boolean setLikeCompany(Long userIdx, String companyId) {
		UserLikeCompanyEntity userCompany = new UserLikeCompanyEntity();
		userCompany.setCompanyIdx(companyId);
		userCompany.setUserIdx(userIdx);
		UserLikeCompanyRepository.save(userCompany);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteLikeCompany(Long userId, String companyId) {
		UserLikeCompanyRepository.deleteByUserIdxAndCompanyIdx(userId.intValue(), companyId);
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public UserinfoDTO getUserinfo(Long userId) {
		UserinfoDTO userinfoDTO = new UserinfoDTO();
		UserEntity userEntity = userRepository.findById(userId.intValue()).get();
		userinfoDTO.setUserName(userEntity.getName());
		userinfoDTO.setEmail(userEntity.getEmail());
		userinfoDTO.setJwt(jwtTokenProvider.createToken(userEntity.getUsername()));
		return userinfoDTO;
	}

	@Override
	@Transactional
	public boolean withdraw(Long userId){
		userRepository.deleteById(userId.intValue());
		return true;
	}

	@Override
	@Transactional
	public UserinfoDTO editUserinfo(Long userId, UpdateUserDTO updateUserDTO){
		UserEntity userEntity = userRepository.findById(userId.intValue()).get();
		userEntity.setEmail(updateUserDTO.getEmail());
		userRepository.save(userEntity);
		UserinfoDTO userinfoDTO = new UserinfoDTO();
		userinfoDTO.setUserName(userEntity.getName());
		userinfoDTO.setEmail(userEntity.getEmail());
		userinfoDTO.setJwt(jwtTokenProvider.createToken(userEntity.getUsername()));
		return userinfoDTO;
	}

	@Override
	@Transactional
	public boolean editPassword(Long userId, String newPassword) {
		UserEntity userEntity = userRepository.findById(userId.intValue()).get();
		userEntity.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(userEntity);
		return true;
	}

}
