package com.DrK.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.DrK.Config.ServiceInit;
import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.DTO.UserinfoDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Entities.UserEntity;
import com.DrK.Entities.UserLikeCompanyEntity;
import com.DrK.lib.TestLib;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
public class UserServiceTest extends ServiceInit{


	@Test
	@Transactional
	public void signupTest() throws Exception {
		SignupDTO signupDTO = new SignupDTO();
		signupDTO.setEmail(TestLib.testUser.email);
		signupDTO.setUserName(TestLib.testUser.name);
		signupDTO.setPassword(TestLib.testUser.password);
		log.info("Signup User DTO");
		log.info(signupDTO.toString());
		
		assertTrue(this.userService.Signup(signupDTO));
		UserEntity userEntity = this.userRepository.findByName(TestLib.testUser.name);
		log.info("Signup User Entity");
		log.info(userEntity.toString());

		assertTrue(userEntity.equals(passwordEncoder, signupDTO));
	}

	@Test
	@Transactional
	public void signinTest() throws Exception {
		this.makeTestUser();
		SigninDTO signinDTO = new SigninDTO();
		signinDTO.setUserName(TestLib.testUser.name);
		signinDTO.setPassword(TestLib.testUser.password);
		final String jwt = this.userService.createToken(signinDTO);
		assertEquals(signinDTO.getUserName(), this.jwtTokenProvider.getUserNameByToken(jwt));
	}

	@Test
	@Transactional
	public void getUserinfoTest() throws Exception {
		UserEntity userEntity = this.makeTestUser();
		log.info(userEntity.toString());
		UserinfoDTO userinfoDTO = this.userService.getUserinfo(TestLib.testUser.name);
		log.info(userinfoDTO.toString());
		assertTrue(userEntity.equals(userinfoDTO));
	}

	@Test
	@Transactional
	public void withdrawTest() throws Exception {
		this.makeTestUser();
		assertTrue(this.userService.withdraw(TestLib.testUser.name, TestLib.testUser.password));
		assertFalse(this.userRepository.isExistUser(TestLib.testUser.name));
	}

	@Test
	@Transactional
	public void editUserinfoTest() throws Exception {
		this.makeTestUser();
		UserinfoDTO userinfoDTO = new UserinfoDTO();
		userinfoDTO.setEmail(TestLib.newTestUser.email);
		userinfoDTO.setUserName(TestLib.newTestUser.name);
		String jwt = this.userService.editUserinfo(TestLib.testUser.name, userinfoDTO);
		assertEquals(TestLib.newTestUser.name, this.jwtTokenProvider.getUserNameByToken(jwt));
		log.info(jwtTokenProvider.getUserNameByToken(jwt));
		UserEntity userEntity = this.userRepository.findByName(TestLib.newTestUser.name);
		log.info(userinfoDTO.toString());
		log.info(userEntity.toString());
		assertTrue(userEntity.equals(userinfoDTO));
	}

	@Test
	@Transactional
	public void editPasswordTest() throws Exception {
		this.makeTestUser();
		assertTrue(this.userService.editPassword(TestLib.testUser.name, TestLib.newTestUser.password));
		UserEntity userEntity = this.userRepository.findByName(TestLib.testUser.name);
		assertTrue(this.passwordEncoder.matches(TestLib.newTestUser.password, userEntity.getPassword()));
	}


	@Test
	@Transactional
	public void setLikeCompanyTest() throws Exception {
		this.makeTestUser();
		CompanyEntity companyEntity = this.makeTestCompany();
		assertTrue(this.userService.setLikeCompany(this.userRepository.findAll().get(0).getIdx(), companyEntity.getId()));
		UserEntity userEntity = this.userRepository.findByName(TestLib.testUser.name);
		for (UserLikeCompanyEntity company : userEntity.getCompanies()) {
			assertEquals(company.getCompanyIdx(), companyEntity.getId());
			assertEquals(company.getUserIdx(), userEntity.getIdx());
		} 
	}

	@Test
	@Transactional
	public void getLikeCompanyTest() throws Exception {
		this.makeTestUser();
		CompanyEntity companyEntity = this.makeTestCompany();
		List<CompanyEntity> companies = this.userService.getUserLikeCompany(TestLib.testUser.name);
		for (CompanyEntity company : companies) {
			assertTrue(company.equals(companyEntity));
		}
	}

	@Test
	@Transactional
	public void deleteLikeCompanyTest() throws Exception {
		this.makeTestUser();
		CompanyEntity companyEntity = this.makeTestCompany();
		this.userService.setLikeCompany(this.userRepository.findAll().get(0).getIdx(), companyEntity.getId());
		this.userService.deleteLikeCompany(TestLib.testUser.name, companyEntity.getId());
		UserEntity userEntity = this.userRepository.findByName(TestLib.testUser.name);
		assertTrue(userEntity.getCompanies().isEmpty());
	}
}
