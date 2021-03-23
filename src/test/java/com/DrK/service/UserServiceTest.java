package com.DrK.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.DrK.Config.ServiceInit;
import com.DrK.DTO.CompanyInfoDTO;
import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.DTO.UpdateUserDTO;
import com.DrK.DTO.UserinfoDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Entities.UserEntity;
import com.DrK.Entities.UserLikeCompanyEntity;
import com.DrK.lib.TestLib;

import org.junit.Test;

public class UserServiceTest extends ServiceInit{


	@Test
	public void signupTest() throws Exception {
		SignupDTO signupDTO = new SignupDTO();
		signupDTO.setEmail(TestLib.testUser.email);
		signupDTO.setUserName(TestLib.testUser.name);
		signupDTO.setPassword(TestLib.testUser.password);
		
		assertTrue(this.userService.signup(signupDTO));
		UserEntity userEntity = this.userRepository.findByName(TestLib.testUser.name);
		assertTrue(this.passwordEncoder.matches(TestLib.testUser.password, userEntity.getPassword()));
	}

	@Test
	public void signinTest() throws Exception {
		this.makeTestUser();
		SigninDTO signinDTO = new SigninDTO();
		signinDTO.setUserName(TestLib.testUser.name);
		signinDTO.setPassword(TestLib.testUser.password);
		final UserinfoDTO userInfo = this.userService.signin(signinDTO);

		assertEquals(TestLib.testUser.name, userInfo.getUserName());
		assertEquals(TestLib.testUser.email, userInfo.getEmail());
		assertEquals(TestLib.testUser.name, this.jwtTokenProvider.getUserNameByToken(userInfo.getJwt()));
	}

	@Test
	public void getUserinfoTest() throws Exception {
		Long userIdx = this.makeTestUser().getIdx();
		UserinfoDTO userInfo = this.userService.getUserinfo(userIdx);

		assertEquals(TestLib.testUser.name, userInfo.getUserName());
		assertEquals(TestLib.testUser.email, userInfo.getEmail());
		assertEquals(TestLib.testUser.name, this.jwtTokenProvider.getUserNameByToken(userInfo.getJwt()));
	}

	@Test
	public void withdrawTest() throws Exception {
		Long userIdx = this.makeTestUser().getIdx();
		assertTrue(this.userService.withdraw(userIdx));
		assertFalse(this.userRepository.isExistUser(TestLib.testUser.name));
	}

	@Test
	public void editUserinfoTest() throws Exception {
		Long userIdx = this.makeTestUser().getIdx();
		UpdateUserDTO updateUserDTO = new UpdateUserDTO();
		updateUserDTO.setEmail(TestLib.newTestUser.email);
		UserinfoDTO userinfoDTO = this.userService.editUserinfo(userIdx, updateUserDTO);

		assertEquals(TestLib.testUser.name, userinfoDTO.getUserName());
		assertEquals(TestLib.newTestUser.email, userinfoDTO.getEmail());
		assertEquals(TestLib.newTestUser.name, this.jwtTokenProvider.getUserNameByToken(userinfoDTO.getJwt()));

		UserEntity userEntity = this.userRepository.findByName(TestLib.testUser.name);
		assertEquals(userEntity.getName(), TestLib.testUser.name);
		assertEquals(userEntity.getEmail(), TestLib.newTestUser.email);
	}

	@Test
	public void editPasswordTest() throws Exception {
		Long userIdx = this.makeTestUser().getIdx();
		assertTrue(this.userService.editPassword(userIdx, TestLib.newTestUser.password));
		UserEntity userEntity = this.userRepository.findByName(TestLib.testUser.name);
		assertTrue(this.passwordEncoder.matches(TestLib.newTestUser.password, userEntity.getPassword()));
	}


	@Test
	public void setLikeCompanyTest() throws Exception {
		Long userIdx = this.makeTestUser().getIdx();
		for(int i=0; i<5; i++){
			CompanyEntity companyEntity = this.makeTestCompany();
			assertTrue(this.userService.setLikeCompany(userIdx, companyEntity.getId()));
		}
		UserEntity userEntity = this.userRepository.findByName(TestLib.testUser.name);
		for (int i=0; i<5; i++) {
			UserLikeCompanyEntity userLikeCompany = userEntity.getCompanies().get(i);
			CompanyEntity company = this.companyRepository.findById(userLikeCompany.getCompanyIdx()).get();
			assertEquals(company.getCompanyName(), TestLib.testCompany.companyName);
			assertEquals(company.getEmployeesNum(), TestLib.testCompany.employeesNum);
			assertEquals(company.getFoundingYear(), TestLib.testCompany.foundingYear);
		} 
	}

	@Test
	public void getLikeCompanyTest() throws Exception {
		Long userIdx = this.makeTestUser().getIdx();
		for(int i=0; i<5; i++){
			CompanyEntity companyEntity = this.makeTestCompany();
			assertTrue(this.userService.setLikeCompany(userIdx, companyEntity.getId()));
		}
		List<CompanyInfoDTO> companies = this.userService.getUserLikeCompany(userIdx);
		for (CompanyInfoDTO company : companies) {
			assertEquals(company.getCompanyName(), TestLib.testCompany.companyName);
			assertEquals(company.getFoundingYear(), TestLib.testCompany.foundingYear);
			assertEquals(company.getEmployeesNum(), TestLib.testCompany.employeesNum);
		}
	}

	@Test
	public void deleteLikeCompanyTest() throws Exception {
		Long userIdx = this.makeTestUser().getIdx();
		CompanyEntity companyEntity = this.makeTestCompany();
		this.userService.setLikeCompany(userIdx, companyEntity.getId());
		this.userService.deleteLikeCompany(userIdx, companyEntity.getId());
		UserEntity userEntity = this.userRepository.findById(userIdx.intValue()).get();
		assertTrue(userEntity.getCompanies().isEmpty());
	}
}
